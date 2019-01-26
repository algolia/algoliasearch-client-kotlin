package com.algolia.search.saas.model.queryRule

import com.algolia.search.saas.model.ObjectID
import kotlinx.serialization.Serializable


@Serializable
data class Promotion(val objectID: ObjectID, val position: Int)