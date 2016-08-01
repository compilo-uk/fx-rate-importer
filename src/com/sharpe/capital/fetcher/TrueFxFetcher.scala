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

  override def getRateBySymbol(symbol: String): FxRate = {

    val sessionId: String = Http(TrueFxBaseUrl).param("u", TrueFxUsername).param("p", TrueFxPassword).param("q", "eurates").asString.body.trim();

    val ratesResponse: HttpResponse[String] = Http(TrueFxBaseUrl).param("id", sessionId).param("f", "csv").param("c", symbol).asString

    println(ratesResponse.body.trim());

    return new FxRate(1.0, 1.0, "AUD/USD", new Date);

  }

  override def getRatesBySymbols(symbol: List[String]): List[FxRate] = {
    return null
  }

}
