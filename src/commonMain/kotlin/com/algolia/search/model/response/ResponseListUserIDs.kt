package com.algolia.search.model.response

import com.algolia.search.serialize.KeyHitsPerPage
import com.algolia.search.serialize.KeyPage
import com.algolia.search.serialize.KeyUserIDs
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
data class ResponseListUserIDs(
    @SerialName(KeyUserIDs) val userIDs: List<ResponseUserID>,
    @Optional @SerialName(KeyPage) val pageOrNull: Int? = null, // TODO Check if actually nullable
    @Optional @SerialName(KeyHitsPerPage) val hitsPerPageOrNull: Int? = null // TODO Check if actually nullable
) {

    @Transient
    val page: Int
        get() = pageOrNull!!

    @Transient
    val hitsPerPage: Int
        get() = hitsPerPageOrNull!!
}