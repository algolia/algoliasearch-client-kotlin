package dsl

import com.algolia.search.dsl.countries
import com.algolia.search.dsl.placesQuery
import shouldNotBeNull
import kotlin.test.Test

internal class TestDSLPlacesQuery {

    @Test
    fun countries() {
        val placesQuery = placesQuery {
            countries {
                +France
            }
        }

        placesQuery.countries.shouldNotBeNull()
    }
}
