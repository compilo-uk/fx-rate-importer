package com.sharpe.capital.test

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen

import com.sharpe.capital.RateFetcher
import com.typesafe.config.ConfigFactory
import com.typesafe.config.Config

class RateFetcherTest extends FeatureSpec with GivenWhenThen {

  info("As a consumer of the FX Rate Importer")
  info("I want to retrieve the current FX rate by ticker symbol")
  info("So that I can use the value within my application")

  private val conf: Config = ConfigFactory.load()

  private val fetcher: RateFetcher = new RateFetcher(conf.getString("true.fx.username"), conf.getString("true.fx.password"))

  feature("Consumer requests FX rate by ticker symbol") {
    scenario("User requests FX rate with valid ticker symbol") {
      assert(fetcher.getBySymbol("AUD/USD") == 1.0)
    }
    //    scenario("User requests FX rate with invalid ticker symbol") {
    //      assert(fetcher.getBySymbol("AUDUSD") == 1.0)
    //    }
  }

}
