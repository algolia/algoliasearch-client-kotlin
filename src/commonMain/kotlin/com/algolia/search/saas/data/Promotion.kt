package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class Promotion(val objectID: ObjectID, val position: Int)