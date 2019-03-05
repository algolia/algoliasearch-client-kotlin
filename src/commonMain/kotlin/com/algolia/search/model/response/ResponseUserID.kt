package com.algolia.search.model.response

import com.algolia.search.model.ClusterName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.UserID
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.JsonObject


@Serializable
data class ResponseUserID(
    @SerialName(KeyUserID) val userID: UserID,
    @SerialName(KeyNbRecords) val nbRecords: Long,
    @SerialName(KeyDataSize) val dataSize: Long,
    @Optional @SerialName(KeyClusterName) val clusterNameOrNull: ClusterName? = null,
    @Optional @SerialName(KeyObjectID) val objectIDOrNull: ObjectID? = null,
    @Optional @SerialName(Key_HighlightResult) val highlightOrNull: JsonObject? = null // TODO Possible typed object
) {

    @Transient
    val clusterName: ClusterName
        get() = clusterNameOrNull!!

    @Transient
    val objectID: ObjectID
        get() = objectIDOrNull!!

    @Transient
    val highlight: JsonObject
        get() = highlightOrNull!!
}