package model.search

import com.algolia.search.model.search.QueryType.Other
import com.algolia.search.model.search.QueryType.PrefixAll
import com.algolia.search.model.search.QueryType.PrefixLast
import com.algolia.search.model.search.QueryType.PrefixNone
import com.algolia.search.serialize.internal.Key
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestQueryType {

    @Test
    fun raw() {
        PrefixLast.raw shouldEqual Key.PrefixLast
        PrefixAll.raw shouldEqual Key.PrefixAll
        PrefixNone.raw shouldEqual Key.PrefixNone
        Other(unknown).raw shouldEqual unknown
    }
}
