package com.algolia.search.dsl.advanced

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.search.ResponseFields

/**
 * DSL for building a [List] of [ResponseFields].
 */
@Suppress("PropertyName")
@DSLParameters
class DSLResponseFields(
    private val responseFields: MutableList<ResponseFields> = mutableListOf()
) {

    val All = ResponseFields.All
    val AroundLatLng = ResponseFields.AroundLatLng
    val AutomaticRadius = ResponseFields.AutomaticRadius
    val ExhaustiveFacetsCount = ResponseFields.ExhaustiveFacetsCount
    val Facets = ResponseFields.Facets
    val FacetsStats = ResponseFields.FacetsStats
    val Hits = ResponseFields.Hits
    val HitsPerPage = ResponseFields.HitsPerPage
    val Index = ResponseFields.Index
    val Length = ResponseFields.Length
    val NbHits = ResponseFields.NbHits
    val NbPages = ResponseFields.NbPages
    val Offset = ResponseFields.Offset
    val Page = ResponseFields.Page
    val Params = ResponseFields.Params
    val ProcessingTimeMS = ResponseFields.ProcessingTimeMS
    val Query = ResponseFields.Query
    val QueryAfterRemoval = ResponseFields.QueryAfterRemoval
    val UserData = ResponseFields.UserData

    /**
     * Add [this] to [responseFields].
     */
    operator fun ResponseFields.unaryPlus() {
        responseFields += this
    }

    companion object : DSL<DSLResponseFields, List<ResponseFields>> {

        override operator fun invoke(block: DSLResponseFields.() -> Unit): List<ResponseFields> {
            return DSLResponseFields().apply(block).responseFields
        }
    }
}
