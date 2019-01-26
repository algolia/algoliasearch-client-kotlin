package client

import com.algolia.search.saas.toUserID
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
import java.text.SimpleDateFormat
import java.util.*


@RunWith(JUnit4::class)
internal class TestClientMultiCluster {

    private val date = SimpleDateFormat("YYYY-MM-DD-HH-mm-ss").also {
        it.timeZone = TimeZone.getTimeZone("UTC")
    }.format(Date())

    private val prefix = "kotlin-$date"
    private val userID = "$prefix-unknown".toUserID()

    @Test
    fun list() {
        runBlocking {
            val clusters = clientMcm.listClusters()

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
            clientMcm.listUserIDs().shouldNotBeEmpty()
            clientMcm.getTopUserID().shouldNotBeEmpty()

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
            clientMcm.listUserIDs().filter { it.userID.raw.startsWith(prefix) }.forEach {
                println(it.userID)
                clientMcm.removeUserID(it.userID)
            }
        }
    }
}