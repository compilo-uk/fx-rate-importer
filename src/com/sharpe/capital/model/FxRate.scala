package com.sharpe.capital.model

class FxRate(a: BigDecimal, b: BigDecimal, s: String) {

  def bid(): BigDecimal = {
    return b
  }

  def ask(): BigDecimal = {
    return a
  }

  def symbol(): String = {
    return s
  }

}
