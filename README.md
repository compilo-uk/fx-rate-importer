# FX Rate Importer
A low-latent & concurrent importer for FX rates, which fetches real-time quotes and persists them in a Cassandra data store.

The importer is implemented using Scala's Fututre class to important the rates as quickly as possible, with minimum blocking operations.

The rates are retrieved from the [True FX API](http://www.truefx.com/), which offers free real-time FX quotes for major currency pairs.

## Distribution / Dependency Management

The library is available in the following Nexus repository:

```
TBC
```

Add this repository to your `pom.xml` file like so:

```
TBC
```

Add this dependency to your `pom.xml` file like so:

```
TBC
```

## Usage / Examples

This library can be used to simply retrieve FX rates from the [True FX API](http://www.truefx.com/), or to persist them to an underlying Cassandra data store.

#### 1. Fetch Single FX Rate

```scala
import com.sharpe.capital.model.FxRate
import com.sharpe.capital.fetcher.RateFetcher
import com.sharpe.capital.fetcher.TrueFxFetcher

...

val fetcher: RateFetcher = new TrueFxFetcher()

var symbol = "AUD/USD"

var rate: FxRate = fetcher.getRateBySymbol(symbol)
```

#### 2. Fetch Many FX Rates

```scala
import com.sharpe.capital.model.FxRate
import com.sharpe.capital.fetcher.RateFetcher
import com.sharpe.capital.fetcher.TrueFxFetcher

...

val fetcher: RateFetcher = new TrueFxFetcher()

var symbols = Array[String]("AUD/USD", "GBP/USD", "EUR/USD")

var rates: Buffer[FxRate] = fetcher.getRatesBySymbols(symbols)
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

#### BDD Tests
Acceptance tests are written in [ScalaTest](http://www.scalatest.org/). Please add acceptance tests for every new feature or bug fix. You can run the test suite by executing `mvn install`.

#### Documentation
Add documentation for every change. Feel free to send corrections or better docs! 

#### Pull Requests
Send _fixes_ PR on the `master` branch.

## License
MIT © [Sharpe Capital](http://sharpecapital.co.uk)
