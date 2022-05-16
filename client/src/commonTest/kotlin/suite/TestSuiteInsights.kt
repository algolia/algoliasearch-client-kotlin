package suite

import clientAdmin1
import clientInsights
import com.algolia.search.helper.toAttribute
import com.algolia.search.helper.toEventName
import com.algolia.search.helper.toObjectID
import com.algolia.search.helper.toUserToken
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.model.internal.Time
import com.algolia.search.model.search.Query
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.internal.Key
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import shouldEqual
import kotlin.test.Test

internal class TestSuiteInsights {

    private val twoDaysInMillis = 172800000
    private val suffix = "insights"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)
    private val userToken = "bar".toUserToken()
    private val eventName = "eventName".toEventName()
    private val objectIDs = listOf("one".toObjectID(), "two".toObjectID())
    private val eventClick = InsightsEvent.Click(
        eventName = "foo".toEventName(),
        userToken = userToken,
        indexName = indexName,
        resources = InsightsEvent.Resources.ObjectIDs(objectIDs),
        timestamp = Time.getCurrentTimeMillis() - twoDaysInMillis
    )
    private val user = clientInsights.User(userToken)
    private val attribute = "dsl/filtering".toAttribute()
    private val filters = listOf(
        Filter.Facet(attribute, "foo"),
        Filter.Facet(attribute, "bar")
    )

    @Test
    fun test() {
        runTest {
            index.apply {
                saveObject(buildJsonObject { put(Key.ObjectID, "one") }).wait() shouldEqual TaskStatus.Published

                clientInsights.sendEvent(eventClick).shouldBeSuccessful()
                clientInsights.sendEvents(listOf(eventClick, eventClick)).shouldBeSuccessful()

                val queryID = search(Query(clickAnalytics = true)).queryID

                user.clickedObjectIDs(indexName, eventName, objectIDs).shouldBeSuccessful()
                user.clickedFilters(indexName, eventName, filters).shouldBeSuccessful()
                user.clickedObjectIDsAfterSearch(indexName, eventName, queryID, objectIDs, listOf(1, 2))
                    .shouldBeSuccessful()

                user.convertedObjectIDs(indexName, eventName, objectIDs).shouldBeSuccessful()
                user.convertedFilters(indexName, eventName, filters).shouldBeSuccessful()
                user.convertedObjectIDsAfterSearch(indexName, eventName, queryID, objectIDs).shouldBeSuccessful()

                user.viewedObjectIDs(indexName, eventName, objectIDs).shouldBeSuccessful()
                user.viewedFilters(indexName, eventName, filters).shouldBeSuccessful()
            }
        }
    }

    private fun HttpResponse.shouldBeSuccessful() {
        status.value shouldEqual HttpStatusCode.OK.value
    }
}
