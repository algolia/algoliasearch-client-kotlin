package com.algolia.search.dsl

import com.algolia.search.model.synonym.SynonymQuery

/**
 * Create a [SynonymQuery] with [block].
 */
public fun synonymQuery(block: SynonymQuery.() -> Unit): SynonymQuery {
    return SynonymQuery().apply(block)
}

/**
 * Assign the output of [block] to [SynonymQuery.synonymTypes].
 */
public fun SynonymQuery.synonymTypes(block: DSLSynonymType.() -> Unit) {
    synonymTypes = DSLSynonymType(block)
}
