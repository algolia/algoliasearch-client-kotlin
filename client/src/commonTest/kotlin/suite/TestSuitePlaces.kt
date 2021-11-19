package suite

import clientPlaces
import com.algolia.search.model.ObjectID
import com.algolia.search.model.places.Country
import com.algolia.search.model.places.PlaceType
import com.algolia.search.model.places.PlacesQuery
import com.algolia.search.model.search.AroundRadius
import com.algolia.search.model.search.Language
import com.algolia.search.model.search.Point
import runTest
import shouldEqual
import shouldNotBeEmpty
import kotlin.test.Test

internal class TestSuitePlaces {

    @Test
    fun withoutParameters() {
        runTest {
            clientPlaces.searchPlaces().hits.shouldNotBeEmpty()
        }
    }

    @Test
    fun withLanguage() {
        runTest {
            clientPlaces.searchPlaces(Language.English).hits.shouldNotBeEmpty()
        }
    }

    @Test
    fun withRankingInfo() {
        runTest {
            clientPlaces.searchPlaces(PlacesQuery(getRankingInfo = true)).hits.shouldNotBeEmpty()
        }
    }

    @Test
    fun withQuery() {
        runTest {
            clientPlaces.searchPlaces(PlacesQuery("New-York")).hits.shouldNotBeEmpty()
        }
    }

    @Test
    fun withCountry() {
        runTest {
            clientPlaces.searchPlaces(PlacesQuery(countries = listOf(Country.UnitedStates))).hits.shouldNotBeEmpty()
        }
    }

    @Test
    fun withType() {
        runTest {
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
        runTest {
            clientPlaces.searchPlaces(PlacesQuery(aroundRadius = AroundRadius.All)).hits.shouldNotBeEmpty()
            clientPlaces.searchPlaces(PlacesQuery(aroundRadius = AroundRadius.InMeters(1000000))).hits.shouldNotBeEmpty()
            clientPlaces.searchPlaces(PlacesQuery(aroundLatLng = Point(0f, 0f))).hits.shouldNotBeEmpty()
            clientPlaces.searchPlaces(PlacesQuery(aroundLatLngViaIP = true)).hits.shouldNotBeEmpty()
        }
    }

    @Test
    fun withAllParameters() {
        runTest {
            val query = PlacesQuery(
                query = "Paris",
                type = PlaceType.City,
                hitsPerPage = 10,
                aroundLatLngViaIP = false,
                aroundLatLng = Point(32.7767f, -96.7970f),
                countries = listOf(Country.France, Country.UnitedStates)
            )
            clientPlaces.searchPlaces(Language.English, query).hits.shouldNotBeEmpty()
        }
    }

    @Test
    fun getByObjectID() {
        runTest {
            clientPlaces.getByObjectID(ObjectID("afd71bb8613f70ca495d8996923b5fd5"))
        }
    }

    @Test
    fun reverseGeocoding() {
        runTest {
            clientPlaces.reverseGeocoding(
                Point(48.880379f, 2.327007f), hitsPerPage = 5
            ).hits.size shouldEqual 5
        }
    }

    @Test
    fun reverseGeocodingLanguage() {
        runTest {
            clientPlaces.reverseGeocoding(
                Language.French,
                Point(48.880379f, 2.327007f),
                hitsPerPage = 5
            ).hits.size shouldEqual 5
        }
    }

    @Test
    fun hitsPerPage() {
        runTest {
            clientPlaces.searchPlaces(PlacesQuery(hitsPerPage = 5)).hits.size shouldEqual 5
        }
    }
}
