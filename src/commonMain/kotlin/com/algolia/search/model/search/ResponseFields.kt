package com.algolia.search.model.search

import com.algolia.search.endpoint.EndpointSearch
import com.algolia.search.model.Raw
import com.algolia.search.serialize.*
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


/**
 * Choose which fields the response will contain. Applies to [EndpointSearch.search] and [EndpointSearch.browse].
 * This parameter is mainly intended to limit the response size. For example, in complex queries, echoing of request
 * parameters in the response’s params field can be undesirable.
 */
@Serializable(ResponseFields.Companion::class)
public sealed class ResponseFields(override val raw: String) : Raw<String> {

    public object All : ResponseFields(KeyStar)
    public object AroundLatLng : ResponseFields(KeyAroundLatLng)
    public object AutomaticRadius : ResponseFields(KeyAutomaticRadius)
    public object ExhaustiveFacetsCount : ResponseFields(KeyExhaustiveFacetsCount)
    public object Facets : ResponseFields(KeyFacets)
    public object FacetsStats : ResponseFields(KeyFacets_Stats)
    public object Hits : ResponseFields(KeyHits)
    public object HitsPerPage : ResponseFields(KeyHitsPerPage)
    public object Index : ResponseFields(KeyIndex)
    public object Length : ResponseFields(KeyLength)
    public object NbHits : ResponseFields(KeyNbHits)
    public object NbPages : ResponseFields(KeyNbPages)
    public object Offset : ResponseFields(KeyOffset)
    public object Page : ResponseFields(KeyPage)
    public object Params : ResponseFields(KeyParams)
    public object ProcessingTimeMS : ResponseFields(KeyProcessingTimeMS)
    public object Query : ResponseFields(KeyQuery)
    public object QueryAfterRemoval : ResponseFields(KeyQueryAfterRemoval)
    public object UserData : ResponseFields(KeyUserData)

    public data class Other(override val raw: String) : ResponseFields(raw)

    override fun toString(): String {
        return raw
    }

    companion object : KSerializer<ResponseFields> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: ResponseFields) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): ResponseFields {
            return when (val string = serializer.deserialize(decoder)) {
                KeyStar -> All
                KeyAroundLatLng -> AroundLatLng
                KeyAutomaticRadius -> AutomaticRadius
                KeyExhaustiveFacetsCount -> ExhaustiveFacetsCount
                KeyFacets -> Facets
                KeyFacets_Stats -> FacetsStats
                KeyHits -> Hits
                KeyHitsPerPage -> HitsPerPage
                KeyIndex -> Index
                KeyLength -> Length
                KeyNbHits -> NbHits
                KeyNbPages -> NbPages
                KeyOffset -> Offset
                KeyPage -> Page
                KeyParams -> Params
                KeyProcessingTimeMS -> ProcessingTimeMS
                KeyQuery -> Query
                KeyQueryAfterRemoval -> QueryAfterRemoval
                KeyUserData -> UserData
                else -> Other(string)
            }
        }
    }
}