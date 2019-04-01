package com.algolia.search.dsl.advanced

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.search.ResponseFields


@Suppress("PropertyName")
@DSLParameters
public class DSLResponseFields(
    private val responseFields: MutableList<ResponseFields> = mutableListOf()
) {

    public val All = ResponseFields.All
    public val AroundLatLng = ResponseFields.AroundLatLng
    public val AutomaticRadius = ResponseFields.AutomaticRadius
    public val ExhaustiveFacetsCount = ResponseFields.ExhaustiveFacetsCount
    public val Facets = ResponseFields.Facets
    public val FacetsStats = ResponseFields.FacetsStats
    public val Hits = ResponseFields.Hits
    public val HitsPerPage = ResponseFields.HitsPerPage
    public val Index = ResponseFields.Index
    public val Length = ResponseFields.Length
    public val NbHits = ResponseFields.NbHits
    public val NbPages = ResponseFields.NbPages
    public val Offset = ResponseFields.Offset
    public val Page = ResponseFields.Page
    public val Params = ResponseFields.Params
    public val ProcessingTimeMS = ResponseFields.ProcessingTimeMS
    public val Query = ResponseFields.Query
    public val QueryAfterRemoval = ResponseFields.QueryAfterRemoval
    public val UserData = ResponseFields.UserData

    public operator fun ResponseFields.unaryPlus() {
        responseFields += this
    }

    public fun build(): List<ResponseFields> {
        return responseFields.toList()
    }
}