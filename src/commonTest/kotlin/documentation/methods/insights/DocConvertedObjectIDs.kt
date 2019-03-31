package documentation.methods.insights

import clientInsights
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.insights.EventName
import com.algolia.search.model.insights.UserToken
import runBlocking
import kotlin.test.Test


internal class DocConvertedObjectIDs {

//    suspend fun ClientInsights.User.convertedObjectIDs(
//        #{indexName}: __IndexName__,
//        #{eventName}: __EventName__,
//        #{objectIDs}: __List<ObjectID>__,
//        timestamp: __Long?__ = null
//    ): HttpResponse

    @Test
    fun convertedObjectIDs() {
        runBlocking {
            val userToken = UserToken("user-id")

            clientInsights.User(userToken).convertedObjectIDs(
                indexName = IndexName("indexName"),
                eventName = EventName("eventName"),
                objectIDs = listOf(ObjectID("objectID1"), ObjectID("objectID2"))
            )
        }
    }
}