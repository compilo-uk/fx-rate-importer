package com.sharpe.capital.fetcher

import com.sharpe.capital.model.FxRate

/**
 * Defines the contract for a rate fetcher implementation
 */
trait RateFetcher {

  /**
   * Returns the exchange rate for a given currency symbol
   */
  def getRateBySymbol(symbol: String): FxRate

  /**
   * Returns a list of exchange rates for a list of given currency symbols
   */
  def getRatesBySymbols(symbol: List[String]): List[FxRate]

}
