package com.sharpe.capital.uat

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen

import com.sharpe.capital.fetcher.RateFetcher
import com.typesafe.config.ConfigFactory
import com.typesafe.config.Config
import com.sharpe.capital.model.FxRate
import com.sharpe.capital.fetcher.TrueFxFetcher
import scala.collection.mutable.Buffer

class TrueFxFetcherTest extends FeatureSpec with GivenWhenThen {

  private val fetcher: RateFetcher = new TrueFxFetcher()

  info("As a consumer of the True FX Fetcher")
  info("I want to retrieve the current FX rate by ticker symbol")
  info("So that I can use the value within my application")

  feature("Consumer requests FX rate by ticker symbol") {

    scenario("User requests FX rate with valid ticker symbol") {

      Given("a valid symbol")
      var symbol = "AUD/USD"

      When("the FX rate is fetched")
      var rate: FxRate = fetcher.getRateBySymbol(symbol)

      Then("the bid / ask rate should be greater than zero")
      assert(rate.ask() > 0)
      assert(rate.bid() > 0)
      assert(rate.ask() >= rate.bid())
      assert(rate.symbol().equals(symbol))

    }

    scenario("User requests FX rate with invalid ticker symbol") {

      Given("an invalid symbol")
      var symbol = "AUD/XXX"

      When("the FX rate is fetched")
      var rate: FxRate = fetcher.getRateBySymbol(symbol)

      Then("the rate is returned as null")
      assert(rate == null)

    }

  }

  feature("Consumer requests multiple FX rates with multiple ticker symbols") {

    scenario("User requests 3 valid ticker symbols") {

      Given("3 valid symbols")
      var symbols = Array[String]("AUD/USD", "GBP/USD", "EUR/USD")

      When("the FX rates are fetched")
      var rates: Buffer[FxRate] = fetcher.getRatesBySymbols(symbols)

      Then("3 rates are received")
      assert(rates.size == 3)

      Then("all bid / ask rates should be greater than zero")
      for (rate <- rates) {
        assert(rate.ask() > 0)
        assert(rate.bid() > 0)
        assert(rate.ask() >= rate.bid())
      }

    }

    scenario("User requests 2 valid ticker symbols, and one invalid symbol") {

      Given("3 valid symbols")
      var symbols = Array[String]("AUD/XXX", "GBP/USD", "EUR/USD")

      When("the FX rates are fetched")
      var rates: Buffer[FxRate] = fetcher.getRatesBySymbols(symbols)

      Then("2 rates are received")
      assert(rates.size == 2)

      Then("all bid / ask rates should be greater than zero")
      for (rate <- rates) {
        assert(rate.ask() > 0)
        assert(rate.bid() > 0)
        assert(rate.ask() >= rate.bid())
      }

    }

  }

}
