package model.filter

import com.algolia.search.model.Attribute
import com.algolia.search.model.filter.internal.escape
import shouldEqual
import kotlin.test.Test

internal class TestFilterInternals {

    @Test
    fun escaping() {
        "value".escape() shouldEqual "\"value\""
        Attribute("value").escape() shouldEqual "\"value\""
    }
}
