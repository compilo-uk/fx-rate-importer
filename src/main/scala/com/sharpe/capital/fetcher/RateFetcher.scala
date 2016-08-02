package com.sharpe.capital.fetcher

import com.sharpe.capital.model.FxRate
import scala.collection.mutable.Buffer

/**
 * Defines the contract for a rate fetcher implementation
 */
trait RateFetcher {

  /**
   * Returns the exchange rate for a given currency symbol
   */
  def getRateBySymbol(symbol: String): FxRate

  /**
   * Returns a buffer of exchange rates for an array of given currency symbols
   */
  def getRatesBySymbols(symbol: Array[String]): Buffer[FxRate]

  /**
   * Returns the FX rate for a given CSV row in String format
   */
  def buildFxRate(fxRow: String): FxRate

}
