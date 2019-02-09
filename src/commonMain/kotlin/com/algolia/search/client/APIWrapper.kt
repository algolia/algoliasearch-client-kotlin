package com.algolia.search.client

import com.algolia.search.host.RetryLogic
import io.ktor.client.HttpClient


internal interface APIWrapper : ConfigurationInterface {

    val httpClient: HttpClient
    val read: RetryLogic
    val write: RetryLogic
}