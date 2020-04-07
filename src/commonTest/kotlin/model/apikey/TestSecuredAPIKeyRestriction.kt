package model.apikey

import com.algolia.search.model.apikey.SecuredAPIKeyRestriction
import com.algolia.search.model.insights.UserToken
import com.algolia.search.model.search.Query
import indexA
import indexB
import shouldEqual
import unknown
import kotlin.test.Test

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
        val restrictSources = "restrictSources=valueA%3BvalueB"
        val restrictIndices = "restrictIndices=indexA%3BindexB"
        val validUntil = "validUntil=0"
        val userToken = "userToken=unknown"

        restriction shouldEqual "$query&$restrictIndices&$restrictSources&$userToken&$validUntil"
    }

    @Test
    fun testAsStrings() {
        val restriction = SecuredAPIKeyRestriction(
            restrictIndices = "indexA;indexB",
            restrictSources = "valueA;valueB",
            query = Query(query = "hello"),
            validUntil = 0,
            userToken = UserToken(unknown)
        ).buildRestrictionString()

        val query = "query=hello"
        val restrictSources = "restrictSources=valueA%3BvalueB"
        val restrictIndices = "restrictIndices=indexA%3BindexB"
        val validUntil = "validUntil=0"
        val userToken = "userToken=unknown"
        restriction shouldEqual "$query&$restrictIndices&$restrictSources&$userToken&$validUntil"
    }

    @Test
    fun testWithoutRestriction() {
        val restriction = SecuredAPIKeyRestriction(
            query = Query(query = "hello"),
            validUntil = 0,
            userToken = UserToken(unknown)
        ).buildRestrictionString()

        val query = "query=hello"
        val validUntil = "validUntil=0"
        val userToken = "userToken=unknown"
        restriction shouldEqual "$query&$userToken&$validUntil"
    }
}
