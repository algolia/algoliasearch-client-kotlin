package com.algolia.search.response.deletion

import com.algolia.search.model.common.Datable
import com.algolia.search.serialize.KeyDeletedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Deletion(@SerialName(KeyDeletedAt) override val date: String) : Datable