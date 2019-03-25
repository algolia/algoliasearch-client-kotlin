package com.algolia.search.configuration

import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.RequestOptions
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.logging.LogLevel


public interface Configuration {

    val applicationID: ApplicationID
    val apiKey: APIKey
    val writeTimeout: Long
    val readTimeout: Long
    val logLevel: LogLevel
    val hosts: List<RetryableHost>
    val engine: HttpClientEngine?
    val httpClient: HttpClient
    val defaultHeaders: Map<String, String>?

    fun RequestOptions?.getTimeout(callType: CallType): Long {
        return when (callType) {
            CallType.Read -> this?.readTimeout ?: readTimeout
            CallType.Write -> this?.writeTimeout ?: writeTimeout
        }
    }
}