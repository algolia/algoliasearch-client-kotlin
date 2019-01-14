package com.algolia.search.saas.client

import com.algolia.search.saas.host.RetryLogic
import io.ktor.client.HttpClient


internal interface Client : Configuration {

    val httpClient: HttpClient
    val read: RetryLogic
    val write: RetryLogic
}