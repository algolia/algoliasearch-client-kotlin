package client.query


enum class SortFacetValuesBy(val raw: String) {
    /**
     * Facet values are sorted by decreasing count.
     * The count is the number of records containing this facet value in the results of the query.
     */
    Count("count"),
    /**
     * Facet values are sorted in alphabetical order, ascending from A to Z.
     * The count is the number of records containing this facet value in the results of the query.
     */
    Alpha("alpha")
}