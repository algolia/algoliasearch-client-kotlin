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

    private val date = SimpleDateFormat("YYYY-MM-DD-HH-mm-ss").format(Date())

    private val prefix = "kotlin-$date"
    private val userID = "$prefix-unknown".toUserID()

    @Test
    fun list() {
        runBlocking {
            val clusters = multiCluster.listClusters()

            (clusters.size >= 2).shouldBeTrue()
            multiCluster.assignUserID(userID, clusters.first().clusterName)
            while (isActive) {
                try {
                    multiCluster.getUserID(userID)
                    break
                } catch (exception: BadResponseStatusException) {
                    exception.statusCode shouldEqual HttpStatusCode.NotFound
                }
                delay(1000L)
            }
            multiCluster.searchUserID(userID.raw).hits.any { it.userID == userID }.shouldBeTrue()
            multiCluster.listUserIDs().shouldNotBeEmpty()
            multiCluster.getTopUserID().shouldNotBeEmpty()

            loop@ while (isActive) {
                try {
                    multiCluster.removeUserID(userID)
                } catch (exception: BadResponseStatusException) {
                    when (exception.statusCode) {
                        HttpStatusCode.NotFound -> break@loop
                        HttpStatusCode.BadRequest -> println(exception.response.readText())
                    }
                }
            }
            while (isActive) {
                try {
                    multiCluster.getUserID(userID)
                } catch (exception: BadResponseStatusException) {
                    exception.statusCode shouldEqual HttpStatusCode.NotFound
                    break
                }
                delay(1000L)
            }
            multiCluster.listUserIDs().filter { it.userID.raw.startsWith(prefix) }.forEach {
                println(it.userID)
                multiCluster.removeUserID(it.userID)
            }
        }
    }
}