package com.algolia.search.saas.model.query_rule

import com.algolia.search.saas.model.ObjectID
import kotlinx.serialization.Serializable


@Serializable
data class Promotion(val objectID: ObjectID, val position: Int)