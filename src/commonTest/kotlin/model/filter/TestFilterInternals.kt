package model.filter

import com.algolia.search.model.Attribute
import com.algolia.search.model.filter.escape
import kotlin.test.Test
import shouldEqual

internal class TestFilterInternals {

    @Test
    fun escaping() {
        "value".escape() shouldEqual "\"value\""
        Attribute("value").escape() shouldEqual "\"value\""
    }
}
