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
data class RequestObjects(
    /**
     * [IndexName] containing the object.
     */
    @SerialName(KeyIndexName) val indexName: IndexName,
    /**
     * The [ObjectID] of the object within that index.
     */
    @SerialName(KeyObjectID) val objectID: ObjectID,
    /**
     *  List of attributes to retrieve. By default, all retrievable attributes are returned.
     */
    @SerialName(KeyAttributesToRetrieve) val attributes: List<Attribute>? = null
)
