package com.algolia.search.saas.model

import kotlinx.serialization.Serializable


@Serializable
data class Promotion(val objectID: ObjectID, val position: Int)