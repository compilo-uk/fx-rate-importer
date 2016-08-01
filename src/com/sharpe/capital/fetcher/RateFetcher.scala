package com.sharpe.capital.fetcher

import scalaj.http.Http
import scalaj.http.HttpResponse
import com.sharpe.capital.model.FxRate
import java.util.Date

/**
 * FX Rate Fetcher class. Abstracts out connectivity to True FX API and
 * exposes methods for returning FX quotes for a currency symbol, or group of symbols
 */
class RateFetcher(username: String, password: String) {

  private val TrueFxBaseUrl: String = "https://webrates.truefx.com/rates/connect.html"

  def getBySymbol(symbol: String): FxRate = {

    val id: String = Http(TrueFxBaseUrl).param("u", username).param("p", password).param("q", "eurates").asString.body.trim();

    val ratesResponse: HttpResponse[String] = Http(TrueFxBaseUrl).param("id", id).param("f", "csv").param("c", symbol).asString

    println(ratesResponse.body.trim());

    return new FxRate(1.0, 1.0, "AUD/USD", new Date);

  }

}
