package suite

import clientPlaces
import com.algolia.search.model.ObjectID
import com.algolia.search.model.places.Country
import com.algolia.search.model.places.PlaceType
import com.algolia.search.model.places.PlacesQuery
import com.algolia.search.model.search.AroundRadius
import com.algolia.search.model.search.Point
import com.algolia.search.model.search.QueryLanguage
import runBlocking
import shouldEqual
import shouldNotBeEmpty
import kotlin.test.Test


internal class TestSuitePlaces {


    @Test
    fun withoutParameters() {
        runBlocking {
            clientPlaces.searchPlaces().hits.shouldNotBeEmpty()
        }
    }

    @Test
    fun withLanguage() {
        runBlocking {
            clientPlaces.searchPlaces(QueryLanguage.English).hits.shouldNotBeEmpty()
        }
    }

    @Test
    fun withRankingInfo() {
        runBlocking {
            clientPlaces.searchPlaces(PlacesQuery(getRankingInfo = true)).hits.shouldNotBeEmpty()
        }
    }

    @Test
    fun withQuery() {
        runBlocking {
            clientPlaces.searchPlaces(PlacesQuery("New-York")).hits.shouldNotBeEmpty()
        }
    }

    @Test
    fun withCountry() {
        runBlocking {
            clientPlaces.searchPlaces(PlacesQuery(countries = listOf(Country.UnitedStates))).hits.shouldNotBeEmpty()
        }
    }

    @Test
    fun withType() {
        runBlocking {
            clientPlaces.searchPlaces(PlacesQuery(type = PlaceType.Country)).hits.shouldNotBeEmpty()
            clientPlaces.searchPlaces(PlacesQuery(type = PlaceType.City)).hits.shouldNotBeEmpty()
            clientPlaces.searchPlaces(PlacesQuery(type = PlaceType.TownHall)).hits.shouldNotBeEmpty()
            clientPlaces.searchPlaces(PlacesQuery(type = PlaceType.TrainStation)).hits.shouldNotBeEmpty()
            clientPlaces.searchPlaces(PlacesQuery(type = PlaceType.BusStop)).hits.shouldNotBeEmpty()
            clientPlaces.searchPlaces(PlacesQuery(type = PlaceType.Address)).hits.shouldNotBeEmpty()
            clientPlaces.searchPlaces(PlacesQuery(type = PlaceType.Airport)).hits.shouldNotBeEmpty()
        }
    }

    @Test
    fun withGeo() {
        runBlocking {
            clientPlaces.searchPlaces(PlacesQuery(aroundRadius = AroundRadius.All)).hits.shouldNotBeEmpty()
            clientPlaces.searchPlaces(PlacesQuery(aroundRadius = AroundRadius.InMeters(1000000))).hits.shouldNotBeEmpty()
            clientPlaces.searchPlaces(PlacesQuery(aroundLatLng = Point(0f, 0f))).hits.shouldNotBeEmpty()
            clientPlaces.searchPlaces(PlacesQuery(aroundLatLngViaIP = true)).hits.shouldNotBeEmpty()
        }
    }

    @Test
    fun withAllParameters() {
        runBlocking {
            val query = PlacesQuery(
                query = "Paris",
                type = PlaceType.City,
                hitsPerPage = 10,
                aroundLatLngViaIP = false,
                aroundLatLng = Point(32.7767f, -96.7970f),
                countries = listOf(Country.France, Country.UnitedStates)
            )
            clientPlaces.searchPlaces(QueryLanguage.English, query).hits.shouldNotBeEmpty()
        }
    }

    @Test
    fun getByObjectID() {
        runBlocking {
            clientPlaces.getByObjectID(ObjectID("141420484_278859695"))
        }
    }

    @Test
    fun reverseGeocoding() {
        runBlocking {
            clientPlaces.reverseGeocoding(
                Point(48.880379f, 2.327007f), hitsPerPage = 5
            ).hits.size shouldEqual 5
        }
    }

    @Test
    fun reverseGeocodingLanguage() {
        runBlocking {
            clientPlaces.reverseGeocoding(
                QueryLanguage.French,
                Point(48.880379f, 2.327007f),
                hitsPerPage = 5
            ).hits.size shouldEqual 5
        }
    }

    @Test
    fun hitsPerPage() {
        runBlocking {
            clientPlaces.searchPlaces(PlacesQuery(hitsPerPage = 5)).hits.size shouldEqual 5
        }
    }
}
