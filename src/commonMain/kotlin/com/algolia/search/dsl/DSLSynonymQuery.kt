package com.algolia.search.dsl

import com.algolia.search.model.synonym.SynonymQuery


public fun synonymQuery(init: SynonymQuery.() -> Unit): SynonymQuery {
    return SynonymQuery().apply(init)
}

public fun SynonymQuery.synonymTypes(block: DSLSynonymType.() -> Unit) {
    synonymTypes = DSLSynonymType().apply(block).build()
}