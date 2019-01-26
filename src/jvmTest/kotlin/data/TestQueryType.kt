package data

import com.algolia.search.saas.model.enums.QueryType.*
import com.algolia.search.saas.serialize.KeyPrefixAll
import com.algolia.search.saas.serialize.KeyPrefixLast
import com.algolia.search.saas.serialize.KeyPrefixNone
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestQueryType {

    @Test
    fun raw() {
        PrefixLast.raw shouldEqual KeyPrefixLast
        PrefixAll.raw shouldEqual KeyPrefixAll
        PrefixNone.raw shouldEqual KeyPrefixNone
        Other(unknown).raw shouldEqual unknown
    }
}