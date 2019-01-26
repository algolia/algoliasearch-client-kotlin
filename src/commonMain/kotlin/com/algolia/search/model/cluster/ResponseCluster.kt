package com.algolia.search.model.cluster

import com.algolia.search.model.ClusterName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.UserID
import com.algolia.search.model.common.Datable
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


sealed class ResponseCluster {

    @Serializable
    data class Get(
        @Optional @SerialName(KeyClusterName) val clusterName: ClusterName? = null,
        @Optional @SerialName(KeyObjectID) val objectID: ObjectID? = null,
        @SerialName(KeyUserID) val userID: UserID,
        @SerialName(KeyNbRecords) val nbRecords: Long,
        @SerialName(KeyDataSize) val dataSize: Long
    )

    @Serializable
    data class GetList(
        @SerialName(KeyUserIDs) val userIDs: List<Get>
    )

    @Serializable
    data class Infos(
        @SerialName(KeyClusters) val infos: List<Info>
    ) {
        @Serializable
        data class Info(
            @SerialName(KeyClusterName) val clusterName: ClusterName,
            @SerialName(KeyNbRecords) val nbRecords: Int,
            @SerialName(KeyNbUserIDs) val nbUserIDs: Long,
            @SerialName(KeyDataSize) val dataSize: Long
        )
    }

    @Serializable
    data class TopUsers(
        @SerialName(KeyTopUsers) val topUsers: Map<ClusterName, List<Get>>
    )

    @Serializable
    data class Search(
        @SerialName(KeyHits) val hits: List<Get>,
        @SerialName(KeyNbHits) val nbHits: Int,
        @SerialName(KeyPage) val page: Int,
        @SerialName(KeyHitsPerPage) val hitsPerPage: Int,
        @SerialName(KeyUpdatedAt) override val date: String
    ) : Datable

    @Serializable
    data class Create(
        @SerialName(KeyCreatedAt) override val date: String
    ) : Datable

    @Serializable
    data class Delete(
        @SerialName(KeyDeletedAt) override val date: String
    ) : Datable
}