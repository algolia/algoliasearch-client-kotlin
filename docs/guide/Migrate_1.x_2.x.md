# Migrate from 1.x to 2.x

Version `2.x` of the library uses the latest ktor version `2.x`. If you want to migrate from
`1.x` to `2.x` please follow these steps:

### Ktor

Refer to Ktor's [migration guide](https://ktor.io/docs/migrating-2.html#feature-plugin-client).

### LogLevel

The library uses its own `LogLevel` instead of ktor's `Loglevel`, please update imports like following:

| Subsystem   | 1.x                                        |                  2.x                  |
|-------------|:-------------------------------------------|:-------------------------------------:|
| Loglevel    | `io.ktor.client.features.logging.LogLevel` | `com.algolia.search.logging.LogLevel` |

### Public constants

Constants (e.g. `KeyIndexName`, `KeyEnglish` and `RouteIndexesV1`) are not exposed anymore. Please use your own
constants instead.  
For your information, those constants are now under `com.algolia.search.serialize.internal` package.

### Recommendation Client

`ClientRecommendation` was deprecated since `v1.9.0` and removed from `v2.0.0`, please use `ClientPersonalization`
instead.
