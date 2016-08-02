package com.sharpe.capital.fetcher

import scalaj.http.Http
import scalaj.http.HttpResponse
import com.sharpe.capital.model.FxRate
import java.util.Date
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import java.util.concurrent.CountDownLatch
import com.sharpe.capital.model.FxRate
import scala.collection.mutable.MutableList
import scala.collection.mutable.Buffer
import scala.collection.mutable.ArrayBuffer

/**
 * FX Rate Fetcher class. Abstracts out connectivity to True FX API and
 * exposes methods for returning FX quotes for a currency symbol, or group of symbols
 */
class TrueFxFetcher() extends RateFetcher {

  private val conf: Config = ConfigFactory.load()

  private val TrueFxBaseUrl: String = conf.getString("true.fx.base.url")
  private val TrueFxUsername: String = conf.getString("true.fx.username")
  private val TrueFxPassword: String = conf.getString("true.fx.password")

  /**
   * { @inheritdoc }
   */
  override def buildFxRate(trueFxRow: String): FxRate = {

    val sections = trueFxRow.split(",")

    val symbol = sections(0)
    val date = new Date(sections(1).toLong / 1000)
    val bid = BigDecimal.exact(sections(2) + sections(3))
    val ask = BigDecimal.exact(sections(4) + sections(5))

    return new FxRate(ask, bid, symbol, date)

  }

  /**
   * { @inheritdoc }
   */
  override def getRateBySymbol(symbol: String): FxRate = {

    val sessionId: String = Http(TrueFxBaseUrl).param("u", TrueFxUsername).param("p", TrueFxPassword).param("q", "eurates").asString.body.trim();

    val ratesResponse: HttpResponse[String] = Http(TrueFxBaseUrl).param("id", sessionId).param("f", "csv").param("c", symbol).asString

    val ratesRow = ratesResponse.body.trim();

    if (ratesRow != null && !ratesRow.isEmpty()) {
      return this.buildFxRate(ratesRow);
    }

    return null;

  }

  /**
   * { @inheritdoc }
   */
  override def getRatesBySymbols(symbols: Array[String]): Buffer[FxRate] = {

    val latch: CountDownLatch = new CountDownLatch(symbols.size)
    val rates: ArrayBuffer[FxRate] = new ArrayBuffer[FxRate]()

    symbols.foreach { symbol => this.completeSymbol(symbol, rates, latch) }

    latch.await()

    return rates

  }

  /**
   * Fetches the FX Rate object for the given symbol in a non-blocking way using a Future implementation
   *
   * @param symbol the currency pair symbol
   * @param rates the final Buffer of FX rates
   * @param latch the count down latch for the asynchronous processing
   */
  private def completeSymbol(symbol: String, rates: Buffer[FxRate], latch: CountDownLatch) {

    val task = Future[FxRate] {
      this.getRateBySymbol(symbol)
    }

    task.onComplete { result => completeRate(result.get, rates, latch) }

  }

  /**
   * Appends the FX rate to the result list if not null, and decrements the count down latch.
   * This is called from within the onComplete function of a Future object
   *
   * @param rate the FX rate to complete
   * @param rates the final Buffer of FX rates
   * @param latch the count down latch for the asynchronous processing
   */
  private def completeRate(rate: FxRate, rates: Buffer[FxRate], latch: CountDownLatch) {

    if (rate != null) {
      rates.append(rate)
    }

    latch.countDown()

  }

}
