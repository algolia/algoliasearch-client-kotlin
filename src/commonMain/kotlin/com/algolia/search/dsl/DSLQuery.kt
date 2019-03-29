package com.algolia.search.dsl

import com.algolia.search.filter.FilterBuilder
import com.algolia.search.filter.OptionalFilterBuilder
import com.algolia.search.model.search.Query


public fun query(init: Query.() -> Unit) = Query().apply(init)

public fun Query.attributesToRetrieve(block: DSLAttributesToRetrieve.() -> Unit) {
    attributesToRetrieve = DSLAttributesToRetrieve().apply(block).build()
}

public fun Query.restrictSearchableAttributes(block: DSLAttributes.() -> Unit) {
    restrictSearchableAttributes = DSLAttributes().apply(block).build()
}

public fun Query.filters(block: FilterBuilder.() -> Unit) {
    filters = FilterBuilder().apply(block).build()
}

public fun Query.optionalFilters(block: OptionalFilterBuilder.() -> Unit) {
    optionalFilters = OptionalFilterBuilder().apply(block).build()
}