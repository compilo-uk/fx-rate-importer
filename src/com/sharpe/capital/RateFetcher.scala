package com.sharpe.capital

import scalaj.http.Http
import scalaj.http.HttpResponse

class RateFetcher(username: String, password: String) {

  private var TrueFxBaseUrl: String = "https://webrates.truefx.com/rates/connect.html"
  private var SessionToken: String = ""

  connect();

  def connect() {
    val tokenResponse: HttpResponse[String] = Http(TrueFxBaseUrl).param("u", username).param("p", password).param("q", "eurates").asString
    this.SessionToken = tokenResponse.body.trim();
  }

  def getBySymbol(symbol: String): Double = {
    val ratesResponse: HttpResponse[String] = Http(TrueFxBaseUrl).param("id", this.SessionToken).param("f", "csv").param("c", symbol).asString
    println(ratesResponse.body.trim());
    return 1.0;
  }

}
