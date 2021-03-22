package com.algolia.search.dsl

import com.algolia.search.dsl.places.DSLCountries
import com.algolia.search.model.places.PlacesQuery

/**
 * Create a [PlacesQuery] with [block] and an optional [query].
 */
public fun placesQuery(query: String? = null, block: PlacesQuery.() -> Unit): PlacesQuery {
    return PlacesQuery(query = query).apply(block)
}

/**
 * Assign the output of [block] to [PlacesQuery.countries].
 */
public fun PlacesQuery.countries(block: DSLCountries.() -> Unit) {
    countries = DSLCountries(block)
}
