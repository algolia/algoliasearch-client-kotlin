package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


@Serializable(ResponseFields.Companion::class)
sealed class ResponseFields(override val raw: String) : RawString {

    object All : ResponseFields(KeyStar)
    object AroundLatLng : ResponseFields(KeyAroundLatLng)
    object AutomaticRadius : ResponseFields(KeyAutomaticRadius)
    object ExhaustiveFacetsCount : ResponseFields(KeyExhaustiveFacetsCount)
    object Facets : ResponseFields(KeyFacets)
    object FacetsStats : ResponseFields(KeyFacetsStats)
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

    data class Unknown(override val raw: String) : ResponseFields(raw)

    override fun toString(): String {
        return raw
    }

    @Serializer(ResponseFields::class)
    internal companion object : KSerializer<ResponseFields> {

        override fun serialize(output: Encoder, obj: ResponseFields) {
            val json = output as JSON.JsonOutput

            json.writeTree(JsonPrimitive(obj.raw))
        }

        override fun deserialize(input: Decoder): ResponseFields {
            val element = input.asJsonInput() as JsonLiteral

            return when (val content = element.content) {
                KeyStar -> All
                KeyAroundLatLng -> AroundLatLng
                KeyAutomaticRadius -> AutomaticRadius
                KeyExhaustiveFacetsCount -> ExhaustiveFacetsCount
                KeyFacets -> Facets
                KeyFacetsStats -> FacetsStats
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
                else -> Unknown(content)
            }
        }
    }
}