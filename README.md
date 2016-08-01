# FX Rate Importer
A low-latent & concurrent importer for FX rates, which fetches real-time quotes and persists them in a Cassandra data store.

The importer is implemented using Scala's Fututre class to important the rates as quickly as possible, with minimum blocking operations.

The rates are retrieved from the [True FX API](http://www.truefx.com/), which offers free real-time FX quotes for major currency pairs.