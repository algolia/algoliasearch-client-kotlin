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
sealed class ResponseFields(override val raw: String) : Raw<String> {

    object All : ResponseFields(KeyStar)
    object AroundLatLng : ResponseFields(KeyAroundLatLng)
    object AutomaticRadius : ResponseFields(KeyAutomaticRadius)
    object ExhaustiveFacetsCount : ResponseFields(KeyExhaustiveFacetsCount)
    object Facets : ResponseFields(KeyFacets)
    object FacetsStats : ResponseFields(KeyFacets_Stats)
    object Hits : ResponseFields(KeyHits)
    object HitsPerPage : ResponseFields(KeyHitsPerPage)
    object Index : ResponseFields(KeyIndex)
    object Length : ResponseFields(KeyLength)
    object NbHits : ResponseFields(KeyNbHits)
    object NbPages : ResponseFields(KeyNbPages)
    object Offset : ResponseFields(KeyOffset)
    object Page : ResponseFields(KeyPage)
    object Params : ResponseFields(KeyParams)
    object ProcessingTimeMS : ResponseFields(KeyProcessingTimeMS)
    object Query : ResponseFields(KeyQuery)
    object QueryAfterRemoval : ResponseFields(KeyQueryAfterRemoval)
    object UserData : ResponseFields(KeyUserData)

    data class Other(override val raw: String) : ResponseFields(raw)

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
