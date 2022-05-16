package com.algolia.search.model.response

import com.algolia.search.model.ObjectID
import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.model.multicluster.UserID
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
public data class ResponseUserID(
    /**
     * [UserID] of the user.
     */
    @SerialName(Key.UserID) val userID: UserID,
    /**
     * Number of records belonging to the user.
     */
    @SerialName(Key.NbRecords) val nbRecords: Long,
    /**
     * Data size used by the user.
     */
    @SerialName(Key.DataSize) val dataSize: Long,
    /**
     * Cluster on which the data of the user is stored.
     */
    @SerialName(Key.ClusterName) val clusterNameOrNull: ClusterName? = null,
    /**
     * [ObjectID] of the requested user. Same as [UserID].
     */
    @SerialName(Key.ObjectID) val objectIDOrNull: ObjectID? = null,
    /**
     * Highlighted attributes.
     */
    @SerialName(Key._HighlightResult)
    val highlightResultsOrNull: JsonObject? = null
) {

    public val clusterName: ClusterName
        get() = clusterNameOrNull!!

    public val objectID: ObjectID
        get() = objectIDOrNull!!

    public val highlightResults: JsonObject
        get() = highlightResultsOrNull!!
}
