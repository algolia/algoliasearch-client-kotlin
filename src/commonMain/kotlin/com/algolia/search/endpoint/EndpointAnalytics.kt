package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.response.ResponseABTest
import com.algolia.search.model.response.ResponseABTests
import com.algolia.search.model.response.creation.CreationABTest
import com.algolia.search.model.response.deletion.DeletionABTest
import com.algolia.search.model.response.revision.RevisionABTest


public interface EndpointAnalytics {

    suspend fun addABTest(abTest: ABTest, requestOptions: RequestOptions? = null): CreationABTest

    suspend fun getABTest(abTestID: ABTestID, requestOptions: RequestOptions? = null): ResponseABTest

    suspend fun stopABTest(abTestID: ABTestID, requestOptions: RequestOptions? = null): RevisionABTest

    suspend fun deleteABTest(abTestID: ABTestID, requestOptions: RequestOptions? = null): DeletionABTest

    suspend fun listABTests(
        page: Int? = null,
        hitsPerPage: Int? = null,
        requestOptions: RequestOptions? = null
    ): ResponseABTests
}