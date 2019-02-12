package suite

import com.algolia.search.toUserID
import io.ktor.client.features.BadResponseStatusException
import io.ktor.client.response.readText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldEqual
import shouldNotBeEmpty
import java.util.*


@RunWith(JUnit4::class)
internal class TestSuiteMultiCluster {

    private val date = dateFormat.format(Date())
    private val prefix = "kotlin-$date"
    private val userID = "$prefix-unknown".toUserID()

    @Test
    fun test() {
        runBlocking {
            val clusters = clientMcm.listClusters().infos

            (clusters.size >= 2).shouldBeTrue()
            clientMcm.assignUserID(userID, clusters.first().clusterName)
            while (isActive) {
                try {
                    clientMcm.getUserID(userID)
                    break
                } catch (exception: BadResponseStatusException) {
                    exception.statusCode shouldEqual HttpStatusCode.NotFound
                }
                delay(1000L)
            }
            clientMcm.searchUserID(userID.raw).hits.any { it.userID == userID }.shouldBeTrue()
            clientMcm.listUserIDs().userIDs.shouldNotBeEmpty()
            clientMcm.getTopUserID().topUsers.shouldNotBeEmpty()

            loop@ while (isActive) {
                try {
                    clientMcm.removeUserID(userID)
                } catch (exception: BadResponseStatusException) {
                    when (exception.statusCode) {
                        HttpStatusCode.NotFound -> break@loop
                        HttpStatusCode.BadRequest -> println(exception.response.readText())
                    }
                }
            }
            while (isActive) {
                try {
                    clientMcm.getUserID(userID)
                } catch (exception: BadResponseStatusException) {
                    exception.statusCode shouldEqual HttpStatusCode.NotFound
                    break
                }
                delay(1000L)
            }
            clientMcm.listUserIDs().userIDs.filter { it.userID.raw.startsWith(prefix) }.forEach {
                println(it.userID)
                clientMcm.removeUserID(it.userID)
            }
        }
    }
}