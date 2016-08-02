package com.sharpe.capital.config

import com.websudos.phantom.connectors.ContactPoints
import com.websudos.phantom.connectors.KeySpaceDef

object Defaults {
  val hosts: Seq[String] = Seq("127.0.0.1")
  val Connector: KeySpaceDef = ContactPoints(hosts).keySpace("fx_rates")
}
