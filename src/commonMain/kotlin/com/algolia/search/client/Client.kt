package com.algolia.search.client

import com.algolia.search.endpoint.ConfigurableEndpoints
import com.algolia.search.host.RetryLogic
import io.ktor.client.HttpClient


internal interface Client : ConfigurationInterface {

    val endpoints: ConfigurableEndpoints
    val httpClient: HttpClient
    val read: RetryLogic
    val write: RetryLogic
}