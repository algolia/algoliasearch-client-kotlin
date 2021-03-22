package com.algolia.search.model.response

import com.algolia.search.model.ObjectID
import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.model.multicluster.UserID
import com.algolia.search.serialize.KeyClusterName
import com.algolia.search.serialize.KeyDataSize
import com.algolia.search.serialize.KeyNbRecords
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.serialize.KeyUserID
import com.algolia.search.serialize.Key_HighlightResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
public data class ResponseUserID(
    /**
     * [UserID] of the user.
     */
    @SerialName(KeyUserID) val userID: UserID,
    /**
     * Number of records belonging to the user.
     */
    @SerialName(KeyNbRecords) val nbRecords: Long,
    /**
     * Data size used by the user.
     */
    @SerialName(KeyDataSize) val dataSize: Long,
    /**
     * Cluster on which the data of the user is stored.
     */
    @SerialName(KeyClusterName) val clusterNameOrNull: ClusterName? = null,
    /**
     * [ObjectID] of the requested user. Same as [UserID].
     */
    @SerialName(KeyObjectID) val objectIDOrNull: ObjectID? = null,
    /**
     * Highlighted attributes.
     */
    @SerialName(Key_HighlightResult)
    val highlightResultsOrNull: JsonObject? = null
) {

    public val clusterName: ClusterName
        get() = clusterNameOrNull!!

    public val objectID: ObjectID
        get() = objectIDOrNull!!

    public val highlightResults: JsonObject
        get() = highlightResultsOrNull!!
}
