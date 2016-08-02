package com.sharpe.capital.repository

import com.websudos.phantom.dsl._
import com.websudos.phantom.connectors.RootConnector
import com.datastax.driver.core.ConsistencyLevel
import scala.concurrent.Future
import com.datastax.driver.core.ResultSet
import org.joda.time.DateTime
import com.sharpe.capital.model.FxRate
import com.sharpe.capital.schema.FxRateTable

abstract class FxRatesRepository extends FxRateTable with RootConnector {

  def store(fxRate: FxRate): Future[ResultSet] = {
    insert.value(_.symbol, fxRate.symbol).value(_.bid, fxRate.bid)
      .value(_.ask, fxRate.ask)
      .value(_.date, fxRate.date)
      .consistencyLevel_=(ConsistencyLevel.ALL)
      .future()
  }

  def getByDateAndSymbol(symbol: String, fromDate: DateTime, toDate: DateTime): Future[List[FxRate]] = {
    select.where(_.date gt fromDate).and(_.date lt toDate).and(_.symbol eqs symbol).fetch()
  }

}
