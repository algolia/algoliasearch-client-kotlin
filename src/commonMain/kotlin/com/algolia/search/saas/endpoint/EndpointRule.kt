package com.algolia.search.saas.endpoint

import com.algolia.search.saas.client.RequestOptions


interface EndpointRule {

    fun saveRule(requestOptions: RequestOptions? = null)
}