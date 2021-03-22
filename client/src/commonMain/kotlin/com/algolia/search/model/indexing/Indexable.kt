package com.algolia.search.model.indexing

import com.algolia.search.endpoint.EndpointIndexing
import com.algolia.search.model.ObjectID

/**
 * Inheritors of this interface can be used with [EndpointIndexing] methods for saving or updating objects.
 */
public interface Indexable {

    public val objectID: ObjectID
}
