# FX Rate Importer
A low-latent & concurrent importer for FX rates, which fetches real-time quotes and persists them in a Cassandra data store.

The importer is implemented using Scala's Fututre class to important the rates as quickly as possible, with minimum blocking operations.

The rates are retrieved from the [True FX API](http://www.truefx.com/), which offers free real-time FX quotes for major currency pairs.

## Distribution Management

The library is available in the following Nexus repository:

```
TBC
```

Add this repository to your `pom.xml` file like so:

```
TBC
```

## Usage / Examples

This library can be used to simply retrieve FX rates from the True FX API, or to persist them to an underlying Cassandra data store.

#### 1. Get Single FX Rate

```scala
val fetcher: RateFetcher = new TrueFxFetcher()

var symbol = "AUD/USD"

var rate: FxRate = fetcher.getRateBySymbol(symbol)

println(rate.ask())
```

#### 2. Get Many FX Rates

```scala
val fetcher: RateFetcher = new TrueFxFetcher()

var symbols = Array[String]("AUD/USD", "GBP/USD", "EUR/USD")

var rates: Buffer[FxRate] = fetcher.getRatesBySymbols(symbols)

for (rate <- rates) {
  println(rate.ask())
}
```

#### 3. Persist Single FX Rate

```
TBC
```

#### 4. Persist Many FX Rates

```
TBC
```

## Contributing

##### Unit tests
Acceptance tests are written in [ScalaTest](http://www.scalatest.org/). Please add acceptance tests for every new feature or bug fix. `mvn install` to run the test suite.  

##### Documentation
Add documentation for every change. Feel free to send corrections or better docs! 

##### Pull Requests
Send _fixes_ PR on the `master` branch.

## License
MIT © [Sharpe Capital](http://sharpecapital.co.uk)
