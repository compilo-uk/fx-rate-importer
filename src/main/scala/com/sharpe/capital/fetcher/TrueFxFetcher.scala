package com.sharpe.capital.fetcher

import scalaj.http.Http
import scalaj.http.HttpResponse
import com.sharpe.capital.model.FxRate
import java.util.Date
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory

/**
 * FX Rate Fetcher class. Abstracts out connectivity to True FX API and
 * exposes methods for returning FX quotes for a currency symbol, or group of symbols
 */
class TrueFxFetcher() extends RateFetcher {

  private val conf: Config = ConfigFactory.load()

  private val TrueFxBaseUrl: String = "https://webrates.truefx.com/rates/connect.html"
  private val TrueFxUsername: String = conf.getString("true.fx.username")
  private val TrueFxPassword: String = conf.getString("true.fx.password")

  private def buildFxRate(trueFxRow: String): FxRate = {

    val sections = trueFxRow.split(",")

    val symbol = sections(0)
    val date = new Date(sections(1).toLong / 1000)
    val bid = BigDecimal.exact(sections(2) + sections(3))
    val ask = BigDecimal.exact(sections(4) + sections(5))

    return new FxRate(ask, bid, symbol, date)

  }

  override def getRateBySymbol(symbol: String): FxRate = {

    val sessionId: String = Http(TrueFxBaseUrl).param("u", TrueFxUsername).param("p", TrueFxPassword).param("q", "eurates").asString.body.trim();

    val ratesResponse: HttpResponse[String] = Http(TrueFxBaseUrl).param("id", sessionId).param("f", "csv").param("c", symbol).asString

    return this.buildFxRate(ratesResponse.body.trim());

  }

  override def getRatesBySymbols(symbol: List[String]): List[FxRate] = {
    return null
  }

}
