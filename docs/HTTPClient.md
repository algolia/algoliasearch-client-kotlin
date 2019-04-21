# Configure the HTTP Client

You can configure which HTTP client to use with the Kotlin client.

he Kotlin client relies on [Ktor](https://ktor.io/) for its HTTP layer.

Ktor offers the ability to choose and configure the underlying HTTP client engine.

The complete list of supported HTTP client engines can be found [here](https://ktor.io/clients/http-client/engines.html).

Note that if you don't specify a client engine, one will be selected by default for you from the available dependencies.

## Apache

```gradle
implementation "io.ktor:ktor-client-apache:$ktor_version"
```

```kotlin
ClientSearch(
    ConfigurationSearch(
        applicationID = ApplicationID("Your Application ID"),
        apiKey = APIKey("Your API key"),
        engine = Apache.create {
            // Pass additional configuration here.
        }
    )
)
```

## OkHttp

```gradle
implementation "io.ktor:ktor-client-okhttp:$ktor_version"
```

```kotlin
ClientSearch(
    ConfigurationSearch(
        applicationID = ApplicationID("Your Application ID"),
        apiKey = APIKey("Your API key"),
        engine = OkHttp.create {
            // Pass additional configuration here.
        }
    )
)
```

## Android HttpUrlConnection

```gradle
implementation "io.ktor:ktor-client-android:$ktor_version"
```

```kotlin
ClientSearch(
    ConfigurationSearch(
        applicationID = ApplicationID("Your Application ID"),
        apiKey = APIKey("Your API key"),
        engine = Android.create {
            // Pass additional configuration here.
        }
    )
)
```
