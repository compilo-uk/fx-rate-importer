package com.sharpe.capital.schema

import com.websudos.phantom.dsl._
import com.sharpe.capital.repository.FxRatesRepository
import com.datastax.driver.core.ConsistencyLevel
import scala.concurrent.Future
import com.datastax.driver.core.ResultSet
import com.sharpe.capital.model.FxRate

class FxRateTable extends CassandraTable[FxRatesRepository, FxRate] {

  object symbol extends StringColumn(this) with PrimaryKey[String]
  object ask extends BigDecimalColumn(this)
  object bid extends BigDecimalColumn(this)
  object date extends DateTimeColumn(this) with PrimaryKey[DateTime]

  override def fromRow(row: Row): FxRate = {
    FxRate(
      symbol(row),
      ask(row),
      bid(row),
      date(row))
  }

}
