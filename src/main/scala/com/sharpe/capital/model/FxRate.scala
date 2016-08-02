package com.sharpe.capital.model

import org.joda.time.DateTime

case class FxRate(
  symbol: String,
  ask: BigDecimal,
  bid: BigDecimal,
  date: DateTime)
