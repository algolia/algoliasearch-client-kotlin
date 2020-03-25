package model.filter

import attributeA
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterConverter
import shouldEqual
import kotlin.test.Test

internal class TestFilterFacetNumber {

    private val filterInt = Filter.Facet(attributeA, 1)
    private val filterLong = Filter.Facet(attributeA, 1L)
    private val filterFloat = Filter.Facet(attributeA, 1f)
    private val filterDouble = !Filter.Facet(attributeA, 1.0)
    private val filterScore = Filter.Facet(attributeA, 1, 2)

    @Test
    fun sql() {
        FilterConverter.SQL(filterInt) shouldEqual "\"attributeA\":1"
        FilterConverter.SQL(filterLong) shouldEqual "\"attributeA\":1"
        FilterConverter.SQL(filterFloat) shouldEqual "\"attributeA\":1.0"
        FilterConverter.SQL(filterDouble) shouldEqual "NOT \"attributeA\":1.0"
        FilterConverter.SQL(filterScore) shouldEqual "\"attributeA\":1<score=2>"
    }

    @Test
    fun legacy() {
        FilterConverter.Legacy(filterInt) shouldEqual listOf("\"attributeA\":1")
        FilterConverter.Legacy(filterLong) shouldEqual listOf("\"attributeA\":1")
        FilterConverter.Legacy(filterFloat) shouldEqual listOf("\"attributeA\":1.0")
        FilterConverter.Legacy(filterDouble) shouldEqual listOf("\"attributeA\":-1.0")
        FilterConverter.Legacy(filterScore) shouldEqual listOf("\"attributeA\":1<score=2>")
    }
}
