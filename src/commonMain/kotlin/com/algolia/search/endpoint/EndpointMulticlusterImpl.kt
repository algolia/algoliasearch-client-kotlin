package com.algolia.search.endpoint

import com.algolia.search.transport.RequestOptions
import com.algolia.search.helper.requestOptionsBuilder
import com.algolia.search.configuration.CallType
import com.algolia.search.transport.Transport
import com.algolia.search.model.ClusterName
import com.algolia.search.model.UserID
import com.algolia.search.model.request.RequestSearchUserID
import com.algolia.search.model.response.*
import com.algolia.search.model.response.creation.Creation
import com.algolia.search.model.response.deletion.Deletion
import com.algolia.search.serialize.*
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json


internal class EndpointMulticlusterImpl(
    private val transport: Transport
) : EndpointMultiCluster {

    override suspend fun listClusters(requestOptions: RequestOptions?): ResponseListClusters {

        return transport.request(HttpMethod.Get, CallType.Read, RouteClustersV1, requestOptions)
    }

    override suspend fun assignUserID(
        userID: UserID,
        clusterName: ClusterName,
        requestOptions: RequestOptions?
    ): Creation {
        val body = json { KeyCluster to clusterName.raw }.toString()
        val options = requestOptionsBuilder(requestOptions) {
            header(KeyAlgoliaUserID, userID.raw)
        }

        return transport.request(HttpMethod.Post, CallType.Write, "$RouteClustersV1/mapping", options, body)
    }

    override suspend fun getUserID(userID: UserID, requestOptions: RequestOptions?): ResponseUserID {
        return transport.request(HttpMethod.Get, CallType.Read, "$RouteClustersV1/mapping/$userID", requestOptions)
    }

    override suspend fun getTopUserID(requestOptions: RequestOptions?): ResponseTopUserID {
        return transport.request(HttpMethod.Get, CallType.Read, "$RouteClustersV1/mapping/top", requestOptions)
    }

    override suspend fun listUserIDs(
        page: Int?,
        hitsPerPage: Int?,
        requestOptions: RequestOptions?
    ): ResponseListUserIDs {
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyPage, page)
            parameter(KeyHitsPerPage, hitsPerPage)
        }

        return transport.request(HttpMethod.Get, CallType.Read, "$RouteClustersV1/mapping", options)
    }

    override suspend fun removeUserID(userID: UserID, requestOptions: RequestOptions?): Deletion {
        val options = requestOptionsBuilder(requestOptions) {
            header(KeyAlgoliaUserID, userID)
        }
        return transport.request(HttpMethod.Delete, CallType.Write, "$RouteClustersV1/mapping", options)
    }

    override suspend fun searchUserID(
        query: String?,
        clusterName: ClusterName?,
        page: Int?,
        hitsPerPage: Int?,
        requestOptions: RequestOptions?
    ): ResponseSearchUserID {
        val path = "$RouteClustersV1/mapping/search"
        val request = RequestSearchUserID(query, clusterName, page, hitsPerPage)
        val body = Json.noDefaults.stringify(RequestSearchUserID.serializer(), request)

        return transport.request(HttpMethod.Post, CallType.Read, path, requestOptions, body)
    }
}