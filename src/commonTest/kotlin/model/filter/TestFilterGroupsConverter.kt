package model.filter

import attributeA
import com.algolia.search.dsl.filters
import com.algolia.search.dsl.numericFilters
import com.algolia.search.dsl.query
import shouldEqual
import kotlin.test.Test


internal class TestFilterGroupsConverter {

    @Test
    fun sql() {
        val query = query {
            filters {
                and { +!range(attributeA, 0..10) }
            }
        }
        query.filters shouldEqual "NOT \"attributeA\":0 TO 10"
    }

    @Test
    fun legacy() {
        val query = query {
            numericFilters {
                and { +!range(attributeA, 0..10) }
            }
        }
        query.numericFilters shouldEqual listOf(listOf("\"attributeA\" < 0"), listOf("\"attributeA\" > 10"))
    }
}