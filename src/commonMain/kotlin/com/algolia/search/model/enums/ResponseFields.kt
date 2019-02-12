package com.algolia.search.model.enums

import com.algolia.search.model.Raw
import com.algolia.search.serialize.*
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


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
            val string = serializer.deserialize(decoder)

            return when (string) {
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