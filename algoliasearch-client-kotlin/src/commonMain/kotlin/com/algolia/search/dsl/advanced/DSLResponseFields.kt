package com.algolia.search.dsl.advanced

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.search.ResponseFields

/**
 * DSL for building a [List] of [ResponseFields].
 */
@Suppress("PropertyName")
@DSLParameters
public class DSLResponseFields(
    private val responseFields: MutableList<ResponseFields> = mutableListOf()
) {

    public val All: ResponseFields.All = ResponseFields.All
    public val AroundLatLng: ResponseFields.AroundLatLng = ResponseFields.AroundLatLng
    public val AutomaticRadius: ResponseFields.AutomaticRadius = ResponseFields.AutomaticRadius
    public val ExhaustiveFacetsCount: ResponseFields.ExhaustiveFacetsCount = ResponseFields.ExhaustiveFacetsCount
    public val Facets: ResponseFields.Facets = ResponseFields.Facets
    public val FacetsStats: ResponseFields.FacetsStats = ResponseFields.FacetsStats
    public val Hits: ResponseFields.Hits = ResponseFields.Hits
    public val HitsPerPage: ResponseFields.HitsPerPage = ResponseFields.HitsPerPage
    public val Index: ResponseFields.Index = ResponseFields.Index
    public val Length: ResponseFields.Length = ResponseFields.Length
    public val NbHits: ResponseFields.NbHits = ResponseFields.NbHits
    public val NbPages: ResponseFields.NbPages = ResponseFields.NbPages
    public val Offset: ResponseFields.Offset = ResponseFields.Offset
    public val Page: ResponseFields.Page = ResponseFields.Page
    public val Params: ResponseFields.Params = ResponseFields.Params
    public val ProcessingTimeMS: ResponseFields.ProcessingTimeMS = ResponseFields.ProcessingTimeMS
    public val Query: ResponseFields.Query = ResponseFields.Query
    public val QueryAfterRemoval: ResponseFields.QueryAfterRemoval = ResponseFields.QueryAfterRemoval
    public val UserData: ResponseFields.UserData = ResponseFields.UserData

    /**
     * Add [this] to [responseFields].
     */
    public operator fun ResponseFields.unaryPlus() {
        responseFields += this
    }

    public companion object : DSL<DSLResponseFields, List<ResponseFields>> {

        override operator fun invoke(block: DSLResponseFields.() -> Unit): List<ResponseFields> {
            return DSLResponseFields().apply(block).responseFields
        }
    }
}
