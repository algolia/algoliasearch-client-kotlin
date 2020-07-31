package com.algolia.search.model.search

import com.algolia.search.endpoint.EndpointSearch
import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyAroundLatLng
import com.algolia.search.serialize.KeyAutomaticRadius
import com.algolia.search.serialize.KeyExhaustiveFacetsCount
import com.algolia.search.serialize.KeyFacets
import com.algolia.search.serialize.KeyFacets_Stats
import com.algolia.search.serialize.KeyHits
import com.algolia.search.serialize.KeyHitsPerPage
import com.algolia.search.serialize.KeyIndex
import com.algolia.search.serialize.KeyLength
import com.algolia.search.serialize.KeyNbHits
import com.algolia.search.serialize.KeyNbPages
import com.algolia.search.serialize.KeyOffset
import com.algolia.search.serialize.KeyPage
import com.algolia.search.serialize.KeyParams
import com.algolia.search.serialize.KeyProcessingTimeMS
import com.algolia.search.serialize.KeyQuery
import com.algolia.search.serialize.KeyQueryAfterRemoval
import com.algolia.search.serialize.KeyStar
import com.algolia.search.serialize.KeyUserData
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

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

    public companion object : KSerializer<ResponseFields> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: ResponseFields) {
            serializer.serialize(encoder, value.raw)
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
