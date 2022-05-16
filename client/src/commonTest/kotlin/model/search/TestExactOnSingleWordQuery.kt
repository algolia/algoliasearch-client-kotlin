package model.search

import com.algolia.search.model.search.ExactOnSingleWordQuery.Attribute
import com.algolia.search.model.search.ExactOnSingleWordQuery.None
import com.algolia.search.model.search.ExactOnSingleWordQuery.Other
import com.algolia.search.model.search.ExactOnSingleWordQuery.Word
import com.algolia.search.serialize.internal.Key
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestExactOnSingleWordQuery {

    @Test
    fun raw() {
        Attribute.raw shouldEqual Key.Attribute
        None.raw shouldEqual Key.None
        Word.raw shouldEqual Key.Word
        Other(unknown).raw shouldEqual unknown
    }
}
