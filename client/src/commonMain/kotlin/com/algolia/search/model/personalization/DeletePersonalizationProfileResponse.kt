package com.algolia.search.model.personalization

import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Delete the user profile response.  */
@Serializable
public data class DeletePersonalizationProfileResponse(
    /** The user token representing the user and associated data.  */
    @SerialName(Key.UserToken) val userToken: String? = null,
    /** Date until which the data can safely be considered as deleted for the given use.  */
    @SerialName(Key.DeletedUntil) val deletedUntil: String? = null
)
