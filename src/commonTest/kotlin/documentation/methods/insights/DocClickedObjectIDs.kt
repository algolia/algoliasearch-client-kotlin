package documentation.methods.insights

import clientInsights
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.insights.EventName
import com.algolia.search.model.insights.UserToken
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocClickedObjectIDs {

//    suspend fun ClientInsights.User.clickedObjectIDs(
//        #{indexName}: __IndexName__,
//        #{eventName}: __EventName__,
//        #{objectIDs}: __List<ObjectID>__,
//        timestamp: __Long__? = null
//    ): HttpResponse

    @Test
    fun clickObjectIDs() {
        runBlocking {
            val userToken = UserToken("user-id")

            clientInsights.User(userToken).clickedObjectIDs(
                indexName = IndexName("indexName"),
                eventName = EventName("eventName"),
                objectIDs = listOf(ObjectID("objectID1"), ObjectID("objectID2"))
            )
        }
    }
}