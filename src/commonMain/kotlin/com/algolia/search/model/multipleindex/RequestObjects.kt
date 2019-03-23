package com.algolia.search.model.multipleindex

import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.serialize.KeyAttributesToRetrieve
import com.algolia.search.serialize.KeyIndexName
import com.algolia.search.serialize.KeyObjectID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class RequestObjects(
    @SerialName(KeyIndexName) val indexName: IndexName,
    @SerialName(KeyObjectID) val objectID: ObjectID,
    @SerialName(KeyAttributesToRetrieve) val attributes: List<Attribute>? = null
)