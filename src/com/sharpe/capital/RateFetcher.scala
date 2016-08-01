package com.sharpe.capital

import scalaj.http.Http
import scalaj.http.HttpResponse

class RateFetcher(username: String, password: String) {

  private val TrueFxBaseUrl: String = "https://webrates.truefx.com/rates/connect.html"

  private var sessionToken: String = _

  connect();

  def connect() {
    val tokenResponse: HttpResponse[String] = Http(TrueFxBaseUrl).param("u", username).param("p", password).param("q", "eurates").asString
    this.sessionToken = tokenResponse.body.trim();
  }

  def getBySymbol(symbol: String): Double = {
    val ratesResponse: HttpResponse[String] = Http(TrueFxBaseUrl).param("id", this.sessionToken).param("f", "csv").param("c", symbol).asString
    println(ratesResponse.body.trim());
    return 1.0;
  }

}
