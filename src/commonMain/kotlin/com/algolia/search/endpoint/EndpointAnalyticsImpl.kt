package com.algolia.search.endpoint

import com.algolia.search.client.*
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.response.ResponseABTest
import com.algolia.search.model.response.ResponseABTests
import com.algolia.search.model.response.creation.CreationABTest
import com.algolia.search.model.response.deletion.DeletionABTest
import com.algolia.search.model.response.revision.RevisionABTest
import com.algolia.search.serialize.KeyLimit
import com.algolia.search.serialize.KeyOffset
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlinx.serialization.json.Json


internal class EndpointAnalyticsImpl(
    val api: APIWrapper
) : EndpointAnalytics,
    APIWrapper by api {

    private val route = "/2/abtests"

    override suspend fun addABTest(abTest: ABTest, requestOptions: RequestOptions?): CreationABTest {
        val bodyString = Json.stringify(ABTest, abTest)

        return retryWrite(requestOptions, route) { url ->
            httpClient.post<CreationABTest>(url) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getABTest(abTestID: ABTestID, requestOptions: RequestOptions?): ResponseABTest {
        return retryRead(requestOptions, "$route/$abTestID") { url ->
            httpClient.get<ResponseABTest>(url) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun stopABTest(abTestID: ABTestID, requestOptions: RequestOptions?): RevisionABTest {
        return retryWrite(requestOptions, "$route/$abTestID/stop") { url ->
            httpClient.post<RevisionABTest>(url) {
                body = ""
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun deleteABTest(abTestID: ABTestID, requestOptions: RequestOptions?): DeletionABTest {
        return retryWrite(requestOptions, "$route/$abTestID") { url ->
            httpClient.delete<DeletionABTest>(url) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun listABTests(page: Int?, hitsPerPage: Int?, requestOptions: RequestOptions?): ResponseABTests {
        return retryRead(requestOptions, route) { url ->
            httpClient.get<ResponseABTests>(url) {
                setRequestOptions(requestOptions)
                parameter(KeyOffset, page)
                parameter(KeyLimit, hitsPerPage)
            }
        }
    }
}