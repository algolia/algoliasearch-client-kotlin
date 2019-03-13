package com.algolia.search.client

import com.algolia.search.host.RetryLogic
import io.ktor.client.HttpClient


internal interface APIWrapper : Configuration {

    val httpClient: HttpClient
    val read: RetryLogic
    val write: RetryLogic
}