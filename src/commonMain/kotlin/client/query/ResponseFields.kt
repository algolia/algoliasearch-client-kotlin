package client.query


enum class ResponseFields(val raw: String) {
    All("*"),
    AroundLatLng("aroundLatLng"),
    AutomaticRadius("automaticRadius"),
    ExhaustiveFacetsCount("exhaustiveFacetsCount"),
    Facets("facets"),
    FacetsStats("facets_stats"),
    Hits("hits"),
    HitsPerPage("hitsPerPage"),
    Index("index"),
    Length("length"),
    NbHits("nbHits"),
    NbPages("nbPages"),
    Offset("offset"),
    Page("page"),
    Params("params"),
    ProcessingTimeMS("processingTimeMS"),
    Query("query"),
    QueryAfterRemoval("queryAfterRemoval"),
    UserData("userData")
}