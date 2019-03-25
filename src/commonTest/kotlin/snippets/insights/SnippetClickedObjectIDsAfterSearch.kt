package snippets.insights

import clientInsights
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.QueryID
import com.algolia.search.model.insights.EventName
import com.algolia.search.model.insights.UserToken
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetClickedObjectIDsAfterSearch : TestSnippets() {

//    suspend fun ClientInsights.User.clickedObjectIDsAfterSearch(
//        #{indexName}: __IndexName__,
//        #{eventName}: __EventName__,
//        #{queryID}: __QueryID__,
//        #{objectIDs}: __List<ObjectID>__,
//        #{positions}: __List<Int>__,
//        timestamp: __Long?__ = null
//    ): HttpResponse

    @Test
    fun clickedObjectIDsAfterSearch() {
        runBlocking {
            val userToken = UserToken("user-id")

            clientInsights.User(userToken).clickedObjectIDsAfterSearch(
                indexName = IndexName("indexName"),
                eventName = EventName("eventName"),
                objectIDs = listOf(ObjectID("objectID1"), ObjectID("objectID2")),
                positions = listOf(17, 19),
                queryID = QueryID("queryID")
            )
        }
    }
}