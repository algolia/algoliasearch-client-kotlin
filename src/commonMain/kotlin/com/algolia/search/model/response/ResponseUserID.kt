package com.algolia.search.model.response

import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.multicluster.UserID
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.JsonObject


@Serializable
public data class ResponseUserID(
    @SerialName(KeyUserID) val userID: UserID,
    @SerialName(KeyNbRecords) val nbRecords: Long,
    @SerialName(KeyDataSize) val dataSize: Long,
    @SerialName(KeyClusterName) val clusterNameOrNull: ClusterName? = null,
    @SerialName(KeyObjectID) val objectIDOrNull: ObjectID? = null,
    @SerialName(Key_HighlightResult) val highlightOrNull: JsonObject? = null // TODO Possible typed object
) {

    @Transient
    public val clusterName: ClusterName
        get() = clusterNameOrNull!!

    @Transient
    public val objectID: ObjectID
        get() = objectIDOrNull!!

    @Transient
    public val highlight: JsonObject
        get() = highlightOrNull!!
}