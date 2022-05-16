package com.algolia.search.model.search

import com.algolia.search.endpoint.EndpointSearch
import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.internal.Key
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

    public object All : ResponseFields(Key.Star)
    public object AroundLatLng : ResponseFields(Key.AroundLatLng)
    public object AutomaticRadius : ResponseFields(Key.AutomaticRadius)
    public object ExhaustiveFacetsCount : ResponseFields(Key.ExhaustiveFacetsCount)
    public object Facets : ResponseFields(Key.Facets)
    public object FacetsStats : ResponseFields(Key.Facets_Stats)
    public object Hits : ResponseFields(Key.Hits)
    public object HitsPerPage : ResponseFields(Key.HitsPerPage)
    public object Index : ResponseFields(Key.Index)
    public object Length : ResponseFields(Key.Length)
    public object NbHits : ResponseFields(Key.NbHits)
    public object NbPages : ResponseFields(Key.NbPages)
    public object Offset : ResponseFields(Key.Offset)
    public object Page : ResponseFields(Key.Page)
    public object Params : ResponseFields(Key.Params)
    public object ProcessingTimeMS : ResponseFields(Key.ProcessingTimeMS)
    public object Query : ResponseFields(Key.Query)
    public object QueryAfterRemoval : ResponseFields(Key.QueryAfterRemoval)
    public object UserData : ResponseFields(Key.UserData)

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
                Key.Star -> All
                Key.AroundLatLng -> AroundLatLng
                Key.AutomaticRadius -> AutomaticRadius
                Key.ExhaustiveFacetsCount -> ExhaustiveFacetsCount
                Key.Facets -> Facets
                Key.Facets_Stats -> FacetsStats
                Key.Hits -> Hits
                Key.HitsPerPage -> HitsPerPage
                Key.Index -> Index
                Key.Length -> Length
                Key.NbHits -> NbHits
                Key.NbPages -> NbPages
                Key.Offset -> Offset
                Key.Page -> Page
                Key.Params -> Params
                Key.ProcessingTimeMS -> ProcessingTimeMS
                Key.Query -> Query
                Key.QueryAfterRemoval -> QueryAfterRemoval
                Key.UserData -> UserData
                else -> Other(string)
            }
        }
    }
}
