package suite

import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.model.search.Query
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.query.FilterFacet
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.helper.toAttribute
import com.algolia.search.helper.toEventName
import com.algolia.search.helper.toObjectID
import com.algolia.search.helper.toUserToken
import io.ktor.client.response.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.json
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestSuiteInsights {

    private val suffix = "insights"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)
    private val userToken = "bar".toUserToken()
    private val eventName = "eventName".toEventName()
    private val objectIDs = listOf("obj1".toObjectID(), "obj2".toObjectID())
    private val eventClick = InsightsEvent.Click(
        eventName = "foo".toEventName(),
        userToken = userToken,
        indexName = indexName,
        resources = InsightsEvent.Resources.ObjectIDs(objectIDs)
    )
    private val user = clientInsights.User(userToken)
    private val attribute = "filter".toAttribute()
    private val filters = listOf(
        FilterFacet(attribute, "foo"),
        FilterFacet(attribute, "bar")
    )

    @Before
    fun clean() {
        runBlocking {
            cleanIndex(clientAdmin1, suffix)
        }
    }

    @Test
    fun test() {
        runBlocking {
            index.apply {
                saveObject(json { KeyObjectID to "one" }).wait() shouldEqual TaskStatus.Published

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