package client.data

import client.serialize.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull


sealed class ResponseFields(open val raw: String) {

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

    internal companion object : Serializer<ResponseFields>, Deserializer<ResponseFields> {

        override fun serialize(input: ResponseFields?): JsonElement {
            return input.unwrap { JsonPrimitive(raw) }
        }

        override fun deserialize(element: JsonElement): ResponseFields? {
            return when (val content = element.contentOrNull) {
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
                null -> null
                else -> Unknown(content)
            }
        }
    }
}