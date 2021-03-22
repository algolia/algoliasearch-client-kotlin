package model.filter

import attributeA
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterConverter
import shouldEqual
import kotlin.test.Test

internal class TestFilterFacetBoolean {

    private val filterTrue = Filter.Facet(attributeA, true)
    private val filterFalse = Filter.Facet(attributeA, false)
    private val filterScore = Filter.Facet(attributeA, true, 4)

    @Test
    fun sql() {
        FilterConverter.SQL(filterTrue) shouldEqual "\"attributeA\":true"
        FilterConverter.SQL(filterFalse) shouldEqual "\"attributeA\":false"
        FilterConverter.SQL(!filterTrue) shouldEqual "NOT \"attributeA\":true"
        FilterConverter.SQL(!filterFalse) shouldEqual "NOT \"attributeA\":false"
        FilterConverter.SQL(filterScore) shouldEqual "\"attributeA\":true<score=4>"
    }

    @Test
    fun legacy() {
        FilterConverter.Legacy(filterTrue) shouldEqual listOf("\"attributeA\":true")
        FilterConverter.Legacy(filterFalse) shouldEqual listOf("\"attributeA\":false")
        FilterConverter.Legacy(!filterTrue) shouldEqual listOf("\"attributeA\":-true")
        FilterConverter.Legacy(!filterFalse) shouldEqual listOf("\"attributeA\":-false")
        FilterConverter.Legacy(filterScore) shouldEqual listOf("\"attributeA\":true<score=4>")
    }
}
