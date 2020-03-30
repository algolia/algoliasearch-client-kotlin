package model.apikey

import com.algolia.search.model.apikey.SecuredAPIKeyRestriction
import com.algolia.search.model.insights.UserToken
import com.algolia.search.model.search.Query
import indexA
import indexB
import kotlin.test.Test
import shouldEqual
import unknown

internal class TestSecuredAPIKeyRestriction {

    @Test
    fun test() {
        val restriction = SecuredAPIKeyRestriction(
            restrictIndices = listOf(indexA, indexB),
            restrictSources = "valueA",
            query = Query(query = "hello"),
            validUntil = 0,
            userToken = UserToken(unknown)
        ).buildRestrictionString()
        val query = "query=hello"
        val restrictSources = "restrictSources=valueA"
        val restrictIndices = "restrictIndices=indexA%3BindexB"
        val validUntil = "validUntil=0"
        val userToken = "userToken=unknown"

        restriction shouldEqual "$query&$restrictIndices&$restrictSources&$userToken&$validUntil"
    }
}
