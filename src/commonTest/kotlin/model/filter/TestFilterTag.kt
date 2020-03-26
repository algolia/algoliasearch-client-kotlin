package model.filter

import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterConverter
import kotlin.test.Test
import shouldEqual

internal class TestFilterTag {

    private val filter = Filter.Tag("valueA")

    @Test
    fun sql() {
        FilterConverter.SQL(filter) shouldEqual "_tags:\"valueA\""
        FilterConverter.SQL(!filter) shouldEqual "NOT _tags:\"valueA\""
    }

    @Test
    fun legacy() {
        FilterConverter.Legacy(filter) shouldEqual listOf("_tags:\"valueA\"")
        FilterConverter.Legacy(!filter) shouldEqual listOf("_tags:-\"valueA\"")
    }
}
