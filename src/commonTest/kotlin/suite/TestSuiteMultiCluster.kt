package suite

import clientMcm
import com.algolia.search.helper.toUserID
import com.algolia.search.model.Time
import io.ktor.client.features.ResponseException
import io.ktor.client.response.readBytes
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.io.core.String
import runBlocking
import shouldBeTrue
import shouldEqual
import shouldNotBeEmpty
import username
import kotlin.test.Test


internal class TestSuiteMultiCluster {

    private val date = DateFormat.format()
    private val prefix = "kotlin-$date"
    private val userID = "$prefix-$username".toUserID()

    @Test
    fun test() {
        runBlocking {
            val clusters = clientMcm.listClusters().clusters

            (clusters.size >= 2).shouldBeTrue()
            clientMcm.assignUserID(userID, clusters.first().name)
            while (isActive) {
                try {
                    clientMcm.getUserID(userID)
                    break
                } catch (exception: ResponseException) {
                    exception.response.status shouldEqual HttpStatusCode.NotFound
                }
                delay(1000L)
            }
            clientMcm.searchUserID(userID.raw).hits.shouldNotBeEmpty()
            clientMcm.listUserIDs().userIDs.shouldNotBeEmpty()
            clientMcm.getTopUserID().topUsers.shouldNotBeEmpty()

            loop@ while (isActive) {
                try {
                    clientMcm.removeUserID(userID)
                } catch (exception: ResponseException) {
                    when (exception.response.status) {
                        HttpStatusCode.NotFound -> break@loop
                        HttpStatusCode.BadRequest -> println(String(exception.response.readBytes()))
                    }
                }
            }
            while (isActive) {
                try {
                    clientMcm.getUserID(userID)
                } catch (exception: ResponseException) {
                    exception.response.status.value shouldEqual HttpStatusCode.NotFound.value
                    break
                }
                delay(1000L)
            }
            clientMcm.listUserIDs().userIDs.filter { it.userID.raw.startsWith(prefix) }.forEach {
                clientMcm.removeUserID(it.userID)
            }
        }
    }
}