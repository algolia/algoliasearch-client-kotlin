package com.algolia.search.model.multipleindex

import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class RequestObjects(
    /**
     * [IndexName] containing the object.
     */
    @SerialName(Key.IndexName) val indexName: IndexName,
    /**
     * The [ObjectID] of the object within that index.
     */
    @SerialName(Key.ObjectID) val objectID: ObjectID,
    /**
     *  List of attributes to retrieve. By default, all retrievable attributes are returned.
     */
    @SerialName(Key.AttributesToRetrieve) val attributes: List<Attribute>? = null
)
