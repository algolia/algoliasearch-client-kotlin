package client.data

import client.serialize.*


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
}