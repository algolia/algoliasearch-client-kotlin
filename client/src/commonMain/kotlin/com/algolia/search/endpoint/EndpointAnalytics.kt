package com.algolia.search.endpoint

import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.response.ResponseABTest
import com.algolia.search.model.response.ResponseABTests
import com.algolia.search.model.response.creation.CreationABTest
import com.algolia.search.model.response.deletion.DeletionABTest
import com.algolia.search.model.response.revision.RevisionABTest
import com.algolia.search.transport.RequestOptions

/**
 * [Documentation][https://www.algolia.com/doc/api-client/methods/ab-test/?language=kotlin]
 */
public interface EndpointAnalytics {

    /**
     * Create an [ABTest].
     * You can set an [ABTest] on two different indices with different settings,
     * or on the same index with different search parameters by providing a
     * [Variant.customSearchParameters] setting on one of the
     * [ABTest.variantA] [ABTest.variantB].
     *
     * @param abTest The definition of the [ABTest].
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun addABTest(
        abTest: ABTest,
        requestOptions: RequestOptions? = null
    ): CreationABTest

    /**
     * Get an [ABTest] information and results.
     *
     * @param abTestID The [ABTestID] that was sent back in the response of the [addABTest] method.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun getABTest(
        abTestID: ABTestID,
        requestOptions: RequestOptions? = null
    ): ResponseABTest

    /**
     * Stop an [ABTest]. Marks the [ABTest] as stopped.
     * At this point, the test is over and cannot be restarted.
     * Additionally, your application is back to normal:
     * index A will perform as usual, receiving 100% of all search requests.
     * Note that stopping is different from deleting: When you stop a test,
     * all associated metadata and metrics are stored and remain accessible.
     *
     * @param abTestID The [ABTestID] that was sent back in the response of the [addABTest] method.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun stopABTest(
        abTestID: ABTestID,
        requestOptions: RequestOptions? = null
    ): RevisionABTest

    /**
     * Delete an [ABTest].
     * Deletes the [ABTest] from your application and removes all associated metadata & metrics.
     * You will therefore no longer be able to view or access the results.
     * Note that deleting a test is different from stopping: When you delete a test,
     * all associated metadata and metrics are deleted.
     *
     * @param abTestID The [ABTestID] that was sent back in the response of the [addABTest] method.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun deleteABTest(
        abTestID: ABTestID,
        requestOptions: RequestOptions? = null
    ): DeletionABTest

    /**
     * List [ABTest] information and results.
     *
     * @param page Specify the first entry to retrieve (0-based, 0 is the most recent entry).
     * @param hitsPerPage Specify the maximum number of entries to retrieve starting at the [page].
     */
    public suspend fun listABTests(
        page: Int? = null,
        hitsPerPage: Int? = null,
        requestOptions: RequestOptions? = null
    ): ResponseABTests
}
