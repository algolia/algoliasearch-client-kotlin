package model.filter

import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterConverter
import shouldEqual
import kotlin.test.Test

internal class TestFilterTag {

    private val filter = Filter.Tag("valueA")
    private val filterQuotation = Filter.Tag("45\"-50\" tv\'s")

    @Test
    fun sql() {
        FilterConverter.SQL(filter) shouldEqual "_tags:\"valueA\""
        FilterConverter.SQL(!filter) shouldEqual "NOT _tags:\"valueA\""
        FilterConverter.SQL(filterQuotation) shouldEqual "_tags:\"45\\\"-50\\\" tv\'s\""
    }

    @Test
    fun legacy() {
        FilterConverter.Legacy(filter) shouldEqual listOf("\"valueA\"")
        FilterConverter.Legacy(!filter) shouldEqual listOf("-\"valueA\"")
        FilterConverter.Legacy(!filter) shouldEqual listOf("-\"valueA\"")
        FilterConverter.Legacy(filterQuotation) shouldEqual listOf("\"45\\\"-50\\\" tv\'s\"")
    }

    @Test
    fun legacyUnquoted() {
        FilterConverter.Legacy.Unquoted(filter) shouldEqual listOf("valueA")
        FilterConverter.Legacy.Unquoted(!filter) shouldEqual listOf("-valueA")
        FilterConverter.Legacy.Unquoted(filterQuotation) shouldEqual listOf("45\\\"-50\\\" tv\'s")
    }
}
