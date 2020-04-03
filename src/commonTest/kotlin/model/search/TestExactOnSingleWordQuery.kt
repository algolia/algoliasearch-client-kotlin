package model.search

import com.algolia.search.model.search.ExactOnSingleWordQuery.Attribute
import com.algolia.search.model.search.ExactOnSingleWordQuery.None
import com.algolia.search.model.search.ExactOnSingleWordQuery.Other
import com.algolia.search.model.search.ExactOnSingleWordQuery.Word
import com.algolia.search.serialize.KeyAttribute
import com.algolia.search.serialize.KeyNone
import com.algolia.search.serialize.KeyWord
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestExactOnSingleWordQuery {

    @Test
    fun raw() {
        Attribute.raw shouldEqual KeyAttribute
        None.raw shouldEqual KeyNone
        Word.raw shouldEqual KeyWord
        Other(unknown).raw shouldEqual unknown
    }
}
