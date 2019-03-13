package com.algolia.search.client

import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import io.ktor.client.features.logging.LogLevel


public interface Configuration {

    val applicationID: ApplicationID
    val apiKey: APIKey
    val writeTimeout: Long
    val readTimeout: Long
    val logLevel: LogLevel
    val hosts: List<String>?

    val RequestOptions?.computedWriteTimeout get() = this?.writeTimeout ?: writeTimeout
    val RequestOptions?.computedReadTimeout get() = this?.readTimeout ?: readTimeout
}