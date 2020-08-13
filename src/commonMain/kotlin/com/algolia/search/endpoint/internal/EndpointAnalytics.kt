@file:Suppress("FunctionName")

package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.dsl.requestOptionsBuilder
import com.algolia.search.endpoint.EndpointAnalytics
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.request.EmptyBody
import com.algolia.search.model.response.ResponseABTest
import com.algolia.search.model.response.ResponseABTests
import com.algolia.search.model.response.creation.CreationABTest
import com.algolia.search.model.response.deletion.DeletionABTest
import com.algolia.search.model.response.revision.RevisionABTest
import com.algolia.search.serialize.Json
import com.algolia.search.serialize.KeyLimit
import com.algolia.search.serialize.KeyOffset
import com.algolia.search.serialize.RouteABTestsV2
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport
import io.ktor.http.HttpMethod

internal class EndpointAnalyticsImpl(
    private val transport: Transport,
) : EndpointAnalytics {

    override suspend fun addABTest(abTest: ABTest, requestOptions: RequestOptions?): CreationABTest {
        val bodyString = Json.encodeToString(ABTest, abTest)

        return transport.request(HttpMethod.Post, CallType.Write, RouteABTestsV2, requestOptions, bodyString)
    }

    override suspend fun getABTest(abTestID: ABTestID, requestOptions: RequestOptions?): ResponseABTest {
        return transport.request(HttpMethod.Get, CallType.Read, "$RouteABTestsV2/$abTestID", requestOptions)
    }

    override suspend fun stopABTest(abTestID: ABTestID, requestOptions: RequestOptions?): RevisionABTest {
        return transport.request(
            HttpMethod.Post,
            CallType.Write,
            "$RouteABTestsV2/$abTestID/stop",
            requestOptions,
            EmptyBody
        )
    }

    override suspend fun deleteABTest(abTestID: ABTestID, requestOptions: RequestOptions?): DeletionABTest {
        return transport.request(HttpMethod.Delete, CallType.Write, "$RouteABTestsV2/$abTestID", requestOptions)
    }

    override suspend fun listABTests(page: Int?, hitsPerPage: Int?, requestOptions: RequestOptions?): ResponseABTests {
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyOffset, page)
            parameter(KeyLimit, hitsPerPage)
        }
        return transport.request(HttpMethod.Get, CallType.Read, RouteABTestsV2, options)
    }
}

/**
 * Create an [EndpointAnalytics] instance.
 */
internal fun EndpointAnalytics(
    transport: Transport,
): EndpointAnalytics = EndpointAnalyticsImpl(transport)
