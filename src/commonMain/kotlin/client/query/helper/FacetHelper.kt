package client.query.helper

import client.query.Query


class FacetHelper(
    vararg facets: String
) : MutableList<String> by facets.toMutableList() {

    fun assign(vararg query: Query) {
        query.forEach { it.facets = this }
    }
}