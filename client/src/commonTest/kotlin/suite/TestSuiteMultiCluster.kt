package suite

import DateFormat
import clientMcm
import com.algolia.search.exception.AlgoliaApiException
import com.algolia.search.helper.toUserID
import com.algolia.search.model.multicluster.UserID
import com.algolia.search.model.multicluster.UserIDQuery
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.test.runTest
import shouldBeFalse
import shouldBeTrue
import shouldEqual
import shouldNotBeEmpty
import username
import kotlin.coroutines.coroutineContext
import kotlin.test.Test

internal class TestSuiteMultiCluster {

    private val date = DateFormat.format()
    private val prefix = "kotlin-$date"

    fun userId(id: Int): UserID {
        return "$prefix-$username-$id".toUserID()
    }

    private suspend fun waitForUserID(userID: UserID) {
        while (coroutineContext.isActive) {
            try {
                clientMcm.getUserID(userID)
                break
            } catch (exception: AlgoliaApiException) {
                exception.httpErrorCode shouldEqual HttpStatusCode.NotFound.value
            }
            delay(1000L)
        }
    }

    private suspend fun removeUserID(userID: UserID) {
        loop@ while (coroutineContext.isActive) {
            try {
                clientMcm.removeUserID(userID)
            } catch (exception: AlgoliaApiException) {
                when (exception.httpErrorCode) {
                    HttpStatusCode.NotFound.value -> break@loop
                    HttpStatusCode.BadRequest.value ->
                        if (exception.message?.contains("is already migrating") == true) {
                            break@loop
                        } else {
                            // Loop until we don't have Error 400: "Another mapping operation is already running for this userID"
                            println(exception.message)
                        }
                }
            }
            delay(1000L)
        }
    }

    @Test
    fun test() {
        runTest {
            val clusters = clientMcm.listClusters().clusters
            val userID0 = userId(0)
            val userID1 = userId(1)
            val userID2 = userId(2)
            val userIDs = listOf(userID0, userID1, userID2)

            (clusters.size >= 2).shouldBeTrue()
            clientMcm.assignUserID(userID0, clusters.first().name)
            clientMcm.assignUserIds(listOf(userID1, userID2), clusters.first().name)
            userIDs.forEach { waitForUserID(it) }
            userIDs.forEach {
                clientMcm.searchUserID(
                    UserIDQuery(
                        query = it.raw,
                        hitsPerPage = 1,
                        clusterName = clusters.first().name
                    )
                ).hits.size shouldEqual 1
            }
            clientMcm.listUserIDs().userIDs.shouldNotBeEmpty()
            clientMcm.getTopUserID().topUsers.shouldNotBeEmpty()
            userIDs.forEach { removeUserID(it) }
            clientMcm.hasPendingMapping(true).clusters.isNullOrEmpty().shouldBeFalse()
        }
    }
}
