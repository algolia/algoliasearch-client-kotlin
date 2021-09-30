package com.algolia.search.model.multipleindex

import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.KeyDefault
import com.algolia.search.serialize.KeyFacet
import com.algolia.search.serialize.KeyIndexName
import com.algolia.search.serialize.KeyQuery
import com.algolia.search.serialize.KeyType
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Associate a typed [Query] to a specific [IndexName].
 */
public sealed interface TypedIndexQuery {
    public val indexName: IndexName
    public val query: Query
    public val type: QueryType
}

/**
 * Associate a hits [Query] to a specific [IndexName].
 */
@Serializable
public class HitIndexQuery(
    @SerialName(KeyIndexName) override val indexName: IndexName,
    @SerialName(KeyQuery) override val query: Query
) : TypedIndexQuery {
    @Required
    @SerialName(KeyType)
    override val type: QueryType = QueryType.Default
}

/**
 * Associate a facets [Query] to a specific [IndexName].
 */
@Serializable
public class FacetIndexQuery(
    @SerialName(KeyIndexName) override val indexName: IndexName,
    @SerialName(KeyQuery) override val query: Query,
    @SerialName(KeyFacet) public val facetAttribute: Attribute
) : TypedIndexQuery {
    @Required
    @SerialName(KeyType)
    override val type: QueryType = QueryType.Facet
}

/**
 * Defines the query types.
 */
@Serializable
public enum class QueryType {

    /** Default type, typically for hits */
    @SerialName(KeyDefault)
    Default,

    /** Facet type */
    @SerialName(KeyFacet)
    Facet
}
