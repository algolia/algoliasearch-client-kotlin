package client.data


sealed class SortFacetValuesBy(open val raw: String) {

    /**
     * FacetFilter values are sorted by decreasing count.
     * The count is the number of records containing this facet value in the results of the query.
     */
    object Count : SortFacetValuesBy("count")

    /**
     * FacetFilter values are sorted in alphabetical order, ascending from A to Z.
     * The count is the number of records containing this facet value in the results of the query.
     */
    object Alpha : SortFacetValuesBy("alpha")

    data class Unknown(override val raw: String) : SortFacetValuesBy(raw)
}