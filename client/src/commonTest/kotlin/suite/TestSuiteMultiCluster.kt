package suite

import DateFormat
import clientMcm
import com.algolia.search.helper.toUserID
import com.algolia.search.model.multicluster.UserID
import com.algolia.search.model.multicluster.UserIDQuery
import io.ktor.client.features.ResponseException
import io.ktor.client.statement.readBytes
import io.ktor.http.HttpStatusCode
import io.ktor.utils.io.core.String
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import runBlocking
import shouldBeFalse
import shouldBeTrue
import shouldEqual
import shouldNotBeEmpty
import username
import kotlin.coroutines.coroutineContext
import kotlin.test.Test

internal class TestSuiteMultiCluster {

    private val date = DateFormat.now()
    private val prefix = "kotlin-$date"

    fun userId(id: Int): UserID {
        return "$prefix-$username-$id".toUserID()
    }

    private suspend fun waitForUserID(userID: UserID) {
        while (coroutineContext.isActive) {
            try {
                clientMcm.getUserID(userID)
                break
            } catch (exception: ResponseException) {
                exception.response.status shouldEqual HttpStatusCode.NotFound
            }
            delay(1000L)
        }
    }

    private suspend fun removeUserID(userID: UserID) {
        loop@ while (coroutineContext.isActive) {
            try {
                clientMcm.removeUserID(userID)
            } catch (exception: ResponseException) {
                val response = exception.response
                when (response.status) {
                    HttpStatusCode.NotFound -> break@loop
                    HttpStatusCode.BadRequest ->
                        if (String(response.readBytes()).contains("is already migrating")) {
                            break@loop
                        } else {
                            // Loop until we don't have Error 400: "Another mapping operation is already running for this userID"
                            println(String(response.readBytes()))
                        }
                }
            }
            delay(1000L)
        }
    }

    @Test
    fun test() {
        runBlocking {
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
