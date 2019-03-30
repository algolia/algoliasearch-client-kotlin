package com.algolia.search.dsl

import com.algolia.search.dsl.filter.DSLFilters
import com.algolia.search.dsl.filter.DSLOptionalFilters
import com.algolia.search.model.search.Query


public fun query(init: Query.() -> Unit) = Query().apply(init)

public fun Query.attributesToRetrieve(block: DSLAttributesToRetrieve.() -> Unit) {
    attributesToRetrieve = DSLAttributesToRetrieve().apply(block).build()
}

public fun Query.restrictSearchableAttributes(block: DSLAttributes.() -> Unit) {
    restrictSearchableAttributes = DSLAttributes().apply(block).build()
}

public fun Query.filters(block: DSLFilters.() -> Unit) {
    filters = DSLFilters().apply(block).build()
}

public fun Query.optionalFilters(block: DSLOptionalFilters.() -> Unit) {
    optionalFilters = DSLOptionalFilters().apply(block).build()
}