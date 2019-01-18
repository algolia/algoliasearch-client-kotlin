package com.algolia.search.saas.client

import com.algolia.search.saas.endpoint.ConfigurableEndpoints
import com.algolia.search.saas.host.RetryLogic
import io.ktor.client.HttpClient


internal interface Client : ConfigurationInterface {

    val endpoints: ConfigurableEndpoints
    val httpClient: HttpClient
    val read: RetryLogic
    val write: RetryLogic
}