package model.places

import com.algolia.search.model.places.PlaceLanguages
import com.algolia.search.model.search.Language
import shouldEqual
import kotlin.test.Test

internal class TestPlaceMulti {

    @Test
    fun dx() {
        val cities = listOf(
            "Paris",
            "New-York"
        )
        val place = PlaceLanguages(cityOrNull = mapOf(Language.French to cities))

        place.city.getValue(Language.French) shouldEqual cities
    }
}
