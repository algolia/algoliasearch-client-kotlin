package com.algolia.search.dsl

import com.algolia.search.model.synonym.SynonymQuery


public fun synonymQuery(block: SynonymQuery.() -> Unit): SynonymQuery {
    return SynonymQuery().apply(block)
}

public fun SynonymQuery.synonymTypes(block: DSLSynonymType.() -> Unit) {
    synonymTypes = DSLSynonymType(block)
}