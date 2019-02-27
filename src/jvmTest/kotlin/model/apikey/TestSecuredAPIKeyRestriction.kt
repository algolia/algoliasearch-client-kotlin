package model.apikey

import com.algolia.search.model.apikey.SecuredAPIKeyRestriction
import com.algolia.search.model.insights.UserToken
import com.algolia.search.model.search.Query
import indexA
import indexB
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestSecuredAPIKeyRestriction {

    @Test
    fun test() {
        val restriction = SecuredAPIKeyRestriction(
            restrictIndices = listOf(indexA, indexB),
            restrictSources = listOf("valueA", "valueB"),
            query = Query(query = "hello"),
            validUntil = 0,
            userToken = UserToken(unknown)
        ).buildRestrictionString()
        val query = "query=hello"
        val restrictSources = "restrictSources=valueA;valueB"
        val restrictIndices = "restrictIndices=indexA;indexB"
        val validUntil = "validUntil=0"
        val userToken = "userToken=unknown"

        restriction shouldEqual "$query&$restrictIndices&$restrictSources&$validUntil&$userToken"
    }
}