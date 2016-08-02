package com.sharpe.capital.fetcher

import com.sharpe.capital.model.FxRate
import scala.collection.mutable.Buffer

/**
 * Defines the contract for a rate fetcher implementation
 */
trait RateFetcher {

  /**
   * Returns the exchange rate for a given currency symbol
   *
   * @param symbol the currency pair's symbol
   *
   * @return the FX Rate object, null if the symbol is invalid
   */
  def getRateBySymbol(symbol: String): FxRate

  /**
   * Returns a buffer of exchange rates for an array of given currency symbols
   *
   * @param symbols an array of currency pair symbols
   *
   * @return a buffer containing the FX Rate objects for valid symbols
   */
  def getRatesBySymbols(symbols: Array[String]): Buffer[FxRate]

  /**
   * Returns the FX rate for a given CSV row in String format
   *
   * @param fxRow a CSV row in String format for the FX rate
   *
   * @return the FX Rate object from parsing the CSV row data
   */
  def buildFxRate(fxRow: String): FxRate

}
