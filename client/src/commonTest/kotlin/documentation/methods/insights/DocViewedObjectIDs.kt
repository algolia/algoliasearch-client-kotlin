package documentation.methods.insights

import clientInsights
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.insights.EventName
import com.algolia.search.model.insights.UserToken
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocViewedObjectIDs {

//    suspend fun ClientInsights.User.viewedObjectIDs(
//        #{indexName}: __IndexName__,
//        #{eventName}: __EventName__,
//        #{objectIDs}: __List<ObjectID>__,
//        timestamp: __Long?__ = null
//    ): HttpResponse

    @Test
    fun snippet1() {
        runTest {
            val userToken = UserToken("user-id")

            clientInsights.User(userToken).viewedObjectIDs(
                indexName = IndexName("indexName"),
                eventName = EventName("eventName"),
                objectIDs = listOf(ObjectID("objectID1"), ObjectID("objectID2"))
            )
        }
    }
}
