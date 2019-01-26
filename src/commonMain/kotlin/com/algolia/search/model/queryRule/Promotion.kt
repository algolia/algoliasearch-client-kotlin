package com.algolia.search.model.queryRule

import com.algolia.search.model.ObjectID
import kotlinx.serialization.Serializable


@Serializable
data class Promotion(val objectID: ObjectID, val position: Int)