package model.search

import com.algolia.search.model.search.QueryType.Other
import com.algolia.search.model.search.QueryType.PrefixAll
import com.algolia.search.model.search.QueryType.PrefixLast
import com.algolia.search.model.search.QueryType.PrefixNone
import com.algolia.search.serialize.KeyPrefixAll
import com.algolia.search.serialize.KeyPrefixLast
import com.algolia.search.serialize.KeyPrefixNone
import kotlin.test.Test
import shouldEqual
import unknown

internal class TestQueryType {

    @Test
    fun raw() {
        PrefixLast.raw shouldEqual KeyPrefixLast
        PrefixAll.raw shouldEqual KeyPrefixAll
        PrefixNone.raw shouldEqual KeyPrefixNone
        Other(unknown).raw shouldEqual unknown
    }
}
