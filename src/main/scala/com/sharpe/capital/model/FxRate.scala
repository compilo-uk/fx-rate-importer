package com.sharpe.capital.model

import java.util.Date

/**
 * Model class to wrap FX quote information
 */
class FxRate(askPrice: BigDecimal, bidPrice: BigDecimal, tickerSymbol: String, quoteDate: Date) {

  def bid(): BigDecimal = {
    return bidPrice
  }

  def ask(): BigDecimal = {
    return askPrice
  }

  def symbol(): String = {
    return tickerSymbol
  }

  def date(): Date = {
    return quoteDate
  }

}
