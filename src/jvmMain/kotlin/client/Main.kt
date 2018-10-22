package client

import io.ktor.client.features.BadResponseStatus
import kotlinx.coroutines.*


fun main(args: Array<String>) {
    val apiKey = ApiKey("dc8e9efcfe38f7fbfb996047af06d8c5")
    val applicationId = ApplicationId("latency")
    val client = Client(applicationId, apiKey)

    runBlocking {
        try {
            client.getListIndexes().let {
                println(it)
            }
        } catch (exception: BadResponseStatus) {
            println(exception.statusCode.value)
            println(exception.statusCode.description)
        }
    }
}