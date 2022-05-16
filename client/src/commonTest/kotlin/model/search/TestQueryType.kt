package model.search

import com.algolia.search.model.search.QueryType.Other
import com.algolia.search.model.search.QueryType.PrefixAll
import com.algolia.search.model.search.QueryType.PrefixLast
import com.algolia.search.model.search.QueryType.PrefixNone
import com.algolia.search.serialize.internal.KeyPrefixAll
import com.algolia.search.serialize.internal.KeyPrefixLast
import com.algolia.search.serialize.internal.KeyPrefixNone
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestQueryType {

    @Test
    fun raw() {
        PrefixLast.raw shouldEqual KeyPrefixLast
        PrefixAll.raw shouldEqual KeyPrefixAll
        PrefixNone.raw shouldEqual KeyPrefixNone
        Other(unknown).raw shouldEqual unknown
    }
}
