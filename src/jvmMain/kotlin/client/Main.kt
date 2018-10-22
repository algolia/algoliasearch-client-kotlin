package client

import io.ktor.client.features.BadResponseStatus
import kotlinx.coroutines.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JSON


@Serializable
data class Stuff(
    val brand: String
)

fun main(args: Array<String>) {
    val apiKey = ApiKey("dc8e9efcfe38f7fbfb996047af06d8c5")
    val applicationId = ApplicationId("latency")
    val client = Client(applicationId, apiKey)
    val index = Index("products")

    runBlocking {
        try {
            val stuff = client.search(index)

            println(stuff.hits.map { JSON.nonstrict.parse<Stuff>(it.serialized) })
        } catch (exception: BadResponseStatus) {
            println(exception.statusCode.value)
            println(exception.statusCode.description)
        }
    }
}