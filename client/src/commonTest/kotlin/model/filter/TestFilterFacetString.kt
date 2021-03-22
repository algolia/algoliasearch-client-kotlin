package model.filter

import attributeA
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterConverter
import shouldEqual
import kotlin.test.Test

internal class TestFilterFacetString {

    private val filter = Filter.Facet(attributeA, "valueA")
    private val filterNegate = !Filter.Facet(attributeA, "valueA")
    private val filterSpace = Filter.Facet(attributeA, "value with space")
    private val filterScore = Filter.Facet(attributeA, "valueA", 1)

    @Test
    fun sql() {
        FilterConverter.SQL(filter) shouldEqual "\"attributeA\":\"valueA\""
        FilterConverter.SQL(filterNegate) shouldEqual "NOT \"attributeA\":\"valueA\""
        FilterConverter.SQL(filterSpace) shouldEqual "\"attributeA\":\"value with space\""
        FilterConverter.SQL(filterScore) shouldEqual "\"attributeA\":\"valueA\"<score=1>"
    }

    @Test
    fun legacy() {
        FilterConverter.Legacy(filter) shouldEqual listOf("\"attributeA\":\"valueA\"")
        FilterConverter.Legacy(filterNegate) shouldEqual listOf("\"attributeA\":-\"valueA\"")
        FilterConverter.Legacy(filterSpace) shouldEqual listOf("\"attributeA\":\"value with space\"")
        FilterConverter.Legacy(filterScore) shouldEqual listOf("\"attributeA\":\"valueA\"<score=1>")
    }

    @Test
    fun legacyUnquoted() {
        FilterConverter.Legacy.Unquoted(filter) shouldEqual listOf("attributeA:valueA")
        FilterConverter.Legacy.Unquoted(filterNegate) shouldEqual listOf("attributeA:-valueA")
        FilterConverter.Legacy.Unquoted(filterSpace) shouldEqual listOf("attributeA:value with space")
        FilterConverter.Legacy.Unquoted(filterScore) shouldEqual listOf("attributeA:valueA<score=1>")
    }
}
