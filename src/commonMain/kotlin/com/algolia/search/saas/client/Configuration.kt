package com.algolia.search.saas.client

import com.algolia.search.saas.data.ApiKey
import com.algolia.search.saas.data.ApplicationId
import io.ktor.client.features.logging.LogLevel


interface Configuration {

    val applicationId: ApplicationId
    val apiKey: ApiKey
    val writeTimeout: Long
    val readTimeout: Long
    val logLevel: LogLevel

    val RequestOptions?.computedWriteTimeout get() = this?.writeTimeout ?: writeTimeout
    val RequestOptions?.computedReadTimeout get() = this?.readTimeout ?: readTimeout
}