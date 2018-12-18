package client.data


sealed class ResponseFields(open val raw: String) {
    object All : ResponseFields("*")
    object AroundLatLng : ResponseFields("aroundLatLng")
    object AutomaticRadius : ResponseFields("automaticRadius")
    object ExhaustiveFacetsCount : ResponseFields("exhaustiveFacetsCount")
    object Facets : ResponseFields("facets")
    object FacetsStats : ResponseFields("facets_stats")
    object Hits : ResponseFields("hits")
    object HitsPerPage : ResponseFields("hitsPerPage")
    object Index : ResponseFields("index")
    object Length : ResponseFields("length")
    object NbHits : ResponseFields("nbHits")
    object NbPages : ResponseFields("nbPages")
    object Offset : ResponseFields("offset")
    object Page : ResponseFields("page")
    object Params : ResponseFields("params")
    object ProcessingTimeMS : ResponseFields("processingTimeMS")
    object Query : ResponseFields("query")
    object QueryAfterRemoval : ResponseFields("queryAfterRemoval")
    object UserData : ResponseFields("userData")

    data class Unknown(override val raw: String) : ResponseFields(raw)
}