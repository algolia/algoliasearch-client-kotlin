package model.filter

import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterConverter
import shouldEqual
import kotlin.test.Test

internal class TestFilterTag {

    private val filter = Filter.Tag("valueA")

    @Test
    fun sql() {
        FilterConverter.SQL(filter) shouldEqual "_tags:\"valueA\""
        FilterConverter.SQL(!filter) shouldEqual "NOT _tags:\"valueA\""
    }

    @Test
    fun legacy() {
        FilterConverter.Legacy(filter) shouldEqual listOf("\"valueA\"")
        FilterConverter.Legacy(!filter) shouldEqual listOf("-\"valueA\"")
    }

    @Test
    fun legacyUnquoted() {
        FilterConverter.Legacy.Unquoted(filter) shouldEqual listOf("valueA")
        FilterConverter.Legacy.Unquoted(!filter) shouldEqual listOf("-valueA")
    }
}
