package com.sharpe.capital.uat

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen

import com.sharpe.capital.fetcher.RateFetcher
import com.typesafe.config.ConfigFactory
import com.typesafe.config.Config
import com.sharpe.capital.model.FxRate
import com.sharpe.capital.fetcher.TrueFxFetcher

class TrueFxFetcherTest extends FeatureSpec with GivenWhenThen {

  private val fetcher: RateFetcher = new TrueFxFetcher()

  info("As a consumer of the True FX Fetcher")
  info("I want to retrieve the current FX rate by ticker symbol")
  info("So that I can use the value within my application")

  feature("Consumer requests FX rate by ticker symbol") {
    scenario("User requests FX rate with valid ticker symbol") {
      var symbol = "AUD/USD"
      var rate: FxRate = fetcher.getRateBySymbol(symbol)
      assert(rate.ask() > 0)
      assert(rate.bid() > 0)
      assert(rate.ask() >= rate.bid())
      assert(rate.symbol().equals(symbol))
    }
    scenario("User requests FX rate with invalid ticker symbol") {
      var symbol = "AUD/XXX"
      var rate: FxRate = fetcher.getRateBySymbol(symbol)
      assert(rate.ask() > 0)
      assert(rate.bid() > 0)
      assert(rate.ask() >= rate.bid())
      assert(rate.symbol().equals(symbol))
    }
  }

  feature("Consumer requests multiple FX rates with multiple ticker symbols") {
    scenario("User requests 3 valid ticker symbols") {
      var symbols = Array[String]("AUD/USD", "GBP/USD", "EUR/USD")
      var rates: Array[FxRate] = fetcher.getRatesBySymbols(symbols)
      assert(rates.size == 3)
    }
  }

}
