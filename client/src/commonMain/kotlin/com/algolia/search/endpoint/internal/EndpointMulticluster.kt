@file:Suppress("FunctionName")

package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.dsl.internal.requestOptionsBuilder
import com.algolia.search.endpoint.EndpointMultiCluster
import com.algolia.search.model.internal.request.RequestAssignUserIDs
import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.model.multicluster.UserID
import com.algolia.search.model.multicluster.UserIDQuery
import com.algolia.search.model.response.ResponseHasPendingMapping
import com.algolia.search.model.response.ResponseListClusters
import com.algolia.search.model.response.ResponseListUserIDs
import com.algolia.search.model.response.ResponseSearchUserID
import com.algolia.search.model.response.ResponseTopUserID
import com.algolia.search.model.response.ResponseUserID
import com.algolia.search.model.response.creation.Creation
import com.algolia.search.model.response.deletion.Deletion
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.Route
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

internal class EndpointMulticlusterImpl(
    private val transport: Transport,
) : EndpointMultiCluster {

    override suspend fun listClusters(requestOptions: RequestOptions?): ResponseListClusters {

        return transport.request(HttpMethod.Get, CallType.Read, Route.ClustersV1, requestOptions)
    }

    override suspend fun assignUserID(
        userID: UserID,
        clusterName: ClusterName,
        requestOptions: RequestOptions?,
    ): Creation {
        val body = buildJsonObject { put(Key.Cluster, clusterName.raw) }.toString()
        val options = requestOptionsBuilder(requestOptions) {
            header(Key.AlgoliaUserID, userID.raw)
        }

        return transport.request(HttpMethod.Post, CallType.Write, "${Route.ClustersV1}/mapping", options, body)
    }

    override suspend fun getUserID(userID: UserID, requestOptions: RequestOptions?): ResponseUserID {
        return transport.request(HttpMethod.Get, CallType.Read, "${Route.ClustersV1}/mapping/$userID", requestOptions)
    }

    override suspend fun getTopUserID(requestOptions: RequestOptions?): ResponseTopUserID {
        return transport.request(HttpMethod.Get, CallType.Read, "${Route.ClustersV1}/mapping/top", requestOptions)
    }

    override suspend fun listUserIDs(
        page: Int?,
        hitsPerPage: Int?,
        requestOptions: RequestOptions?,
    ): ResponseListUserIDs {
        val options = requestOptionsBuilder(requestOptions) {
            parameter(Key.Page, page)
            parameter(Key.HitsPerPage, hitsPerPage)
        }

        return transport.request(HttpMethod.Get, CallType.Read, "${Route.ClustersV1}/mapping", options)
    }

    override suspend fun removeUserID(userID: UserID, requestOptions: RequestOptions?): Deletion {
        val options = requestOptionsBuilder(requestOptions) {
            header(Key.AlgoliaUserID, userID)
        }
        return transport.request(HttpMethod.Delete, CallType.Write, "${Route.ClustersV1}/mapping", options)
    }

    override suspend fun searchUserID(
        query: UserIDQuery,
        requestOptions: RequestOptions?,
    ): ResponseSearchUserID {
        val path = "${Route.ClustersV1}/mapping/search"
        val body = JsonNoDefaults.encodeToString(UserIDQuery.serializer(), query)

        return transport.request(HttpMethod.Post, CallType.Read, path, requestOptions, body)
    }

    override suspend fun assignUserIds(
        userIDs: List<UserID>,
        clusterName: ClusterName,
        requestOptions: RequestOptions?,
    ): Creation {
        val path = "${Route.ClustersV1}/mapping/batch"
        val request = RequestAssignUserIDs(clusterName, userIDs)
        val body = JsonNoDefaults.encodeToString(RequestAssignUserIDs.serializer(), request)

        return transport.request(HttpMethod.Post, CallType.Write, path, requestOptions, body)
    }

    override suspend fun hasPendingMapping(
        retrieveMapping: Boolean,
        requestOptions: RequestOptions?,
    ): ResponseHasPendingMapping {
        val path = "${Route.ClustersV1}/mapping/pending"
        val options = requestOptionsBuilder(requestOptions) {
            parameter(Key.GetClusters, retrieveMapping)
        }

        return transport.request(HttpMethod.Get, CallType.Read, path, options)
    }
}

/**
 * Create an [EndpointMulticluster] instance.
 */
internal fun EndpointMulticluster(
    transport: Transport,
): EndpointMultiCluster = EndpointMulticlusterImpl(transport)
