package com.algolia.search.model.response

import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.model.multicluster.UserID
import com.algolia.search.model.search.HighlightResult
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


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
    @Serializable(KSerializerHighlightResults::class)
    val highlightResultsOrNull:  Map<Attribute, List<HighlightResult>>? = null
) {

    @Transient
    public val clusterName: ClusterName
        get() = clusterNameOrNull!!

    @Transient
    public val objectID: ObjectID
        get() = objectIDOrNull!!

    @Transient
    public val highlightResults:  Map<Attribute, List<HighlightResult>>
        get() = highlightResultsOrNull!!
}