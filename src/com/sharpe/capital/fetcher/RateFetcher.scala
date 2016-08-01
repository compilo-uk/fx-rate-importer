package com.sharpe.capital.fetcher

import com.sharpe.capital.model.FxRate

trait RateFetcher {

  def getBySymbol(symbol: String): FxRate

}
