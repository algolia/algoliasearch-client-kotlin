package com.algolia.search.endpoint

import com.algolia.search.model.personalization.PersonalizationStrategy
import com.algolia.search.model.response.ResponsePersonalizationStrategy
import com.algolia.search.model.response.revision.Revision
import com.algolia.search.transport.RequestOptions


public interface EndpointPersonalization {

    suspend fun setPersonalizationStrategy(
        strategy: PersonalizationStrategy,
        requestOptions: RequestOptions? = null
    ): Revision

    suspend fun getPersonalizationStrategy(
        requestOptions: RequestOptions? = null
    ): ResponsePersonalizationStrategy
}