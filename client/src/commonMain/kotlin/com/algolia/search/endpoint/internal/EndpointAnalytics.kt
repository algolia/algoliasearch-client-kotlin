@file:Suppress("FunctionName")

package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.dsl.internal.requestOptionsBuilder
import com.algolia.search.endpoint.EndpointAnalytics
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.internal.request.EmptyBody
import com.algolia.search.model.response.ResponseABTest
import com.algolia.search.model.response.ResponseABTests
import com.algolia.search.model.response.creation.CreationABTest
import com.algolia.search.model.response.deletion.DeletionABTest
import com.algolia.search.model.response.revision.RevisionABTest
import com.algolia.search.serialize.internal.Json
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.Route
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.http.HttpMethod

internal class EndpointAnalyticsImpl(
    private val transport: Transport,
) : EndpointAnalytics {

    override suspend fun addABTest(abTest: ABTest, requestOptions: RequestOptions?): CreationABTest {
        val bodyString = Json.encodeToString(ABTest, abTest)

        return transport.request(HttpMethod.Post, CallType.Write, Route.ABTestsV2, requestOptions, bodyString)
    }

    override suspend fun getABTest(abTestID: ABTestID, requestOptions: RequestOptions?): ResponseABTest {
        return transport.request(HttpMethod.Get, CallType.Read, "${Route.ABTestsV2}/$abTestID", requestOptions)
    }

    override suspend fun stopABTest(abTestID: ABTestID, requestOptions: RequestOptions?): RevisionABTest {
        return transport.request(
            HttpMethod.Post, CallType.Write, "${Route.ABTestsV2}/$abTestID/stop", requestOptions, EmptyBody
        )
    }

    override suspend fun deleteABTest(abTestID: ABTestID, requestOptions: RequestOptions?): DeletionABTest {
        return transport.request(HttpMethod.Delete, CallType.Write, "${Route.ABTestsV2}/$abTestID", requestOptions)
    }

    override suspend fun listABTests(page: Int?, hitsPerPage: Int?, requestOptions: RequestOptions?): ResponseABTests {
        val options = requestOptionsBuilder(requestOptions) {
            parameter(Key.Offset, page)
            parameter(Key.Limit, hitsPerPage)
        }
        return transport.request(HttpMethod.Get, CallType.Read, Route.ABTestsV2, options)
    }
}

/**
 * Create an [EndpointAnalytics] instance.
 */
internal fun EndpointAnalytics(
    transport: Transport,
): EndpointAnalytics = EndpointAnalyticsImpl(transport)
