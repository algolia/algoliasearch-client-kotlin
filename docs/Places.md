# Places client

Algolia Places provides a fast, distributed and easy way to use address search.

## Pricing

#### Rate limit

The Algolia Places API enforces 30 queries per second. [Contact us](https://community.algolia.com/places/contact.html) if you need more.

If you're calling the API from your backend, the rate-limits computation is then based on the source IP.

#### Plans

- Free: 1000 requests / day [Sign Up](https://www.algolia.com/users/sign_up/places)
- Free, with authentication: 100 000 requests / month [Sign Up](https://www.algolia.com/users/sign_up/places)
- Paying: $0.40 per 1000 requests [Sign Up](https://www.algolia.com/users/sign_up/places)
- Up to unlimited:  [Contact us](https://community.algolia.com/places/contact.html)


Visit our [website](https://community.algolia.com/places/pricing.html).

## Usage

#### Unauthenticated

You can use Algolia Places without authentication. Limited to 1000 requests per day.

```kotlin
val client = ClientPlaces()
```

#### Authenticated

Pass an `ApplicationID` and an `APIKey` to the `ClientPlaces` for authenticated usage.

```kotlin
val client = ClientPlaces(
    ApplicationID("YourApplicationID"),
    APIKey("YourPlacesAPIKey")
)
```

#### Search places for multiple languages

By default, the response of `searchPlaces` contains translations in all languages. 

```kotlin
val response = client.searchPlaces(PlacesQuery("Paris"))

response.hits.first().city.getValue(Language.English)
```

#### Search places for one language

However, it is possible to restrict the search results to a single language.

```kotlin
val response = client.searchPlaces(
    query = PlacesQuery("New-York"),
    language = Language.English
)

response.hits.first().city
```

#### Search places in countries

Unless one or multiple countries are specified, it will search on the whole planet.

```kotlin
val query = placesQuery("York") {
    countries {
        +UnitedKingdom
        +UnitedStates
    }
}

client.searchPlaces(query)
```

#### Search places around radius

Use latitude and longitude coordinates to find places.

```kotlin
val query = placesQuery {
    aroundLatLng = Point(40.7128f, -74.0060f) // New-York
}

client.searchPlaces(query)
```

#### Reverse geocoding

Reverse geocoding means converting a location (latitude and longitude) to a readable address.

```kotlin
client.reverseGeocoding(Point(40.7128f, -74.0060f)) // New-York
```

#### Get By ObjectID

Use a Places `objectID` to get an Algolia Places record.

```kotlin
 clientPlaces.getByObjectID(ObjectID("201316654_7340078")) // New-York
```

