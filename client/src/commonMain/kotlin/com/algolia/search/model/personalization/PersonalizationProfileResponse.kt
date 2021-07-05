package com.algolia.search.model.personalization

import com.algolia.search.model.insights.UserToken
import com.algolia.search.serialize.KeyLastEventAt
import com.algolia.search.serialize.KeyScores
import com.algolia.search.serialize.KeyUserToken
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/**
 * User profile built from Personalization strategy.
 */
@Serializable
public data class PersonalizationProfileResponse(
    /**
     * The [UserToken] representing the user and associated data.
     */
    @SerialName(KeyUserToken) public val userToken: UserToken,
    /**
     * The last processed event timestamp using the ISO 8601 format.
     */
    @SerialName(KeyLastEventAt) public val lastEventAt: String,
    /**
     * The profile is structured by facet name used in the strategy. Each facet value is mapped to its score.
     * Each score represents the user affinity for a specific facet value given the [userToken] past events and
     * the Personalization strategy defined. Scores are bounded to 20.
     */
    @SerialName(KeyScores) public val scores: JsonObject
)
