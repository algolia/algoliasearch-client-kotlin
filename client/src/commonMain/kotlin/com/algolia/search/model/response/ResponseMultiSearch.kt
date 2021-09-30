package com.algolia.search.model.multipleindex

import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchForFacets
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
public class ResponseMultiSearch(
    public val results: List<ResultMultiSearch>
)

@Serializable(ResultMultiSearch.Companion::class)
public sealed class ResultMultiSearch {
    public class Hits(public val response: ResponseSearch) : ResultMultiSearch()
    public class Facets(public val response: ResponseSearchForFacets) : ResultMultiSearch()

    public companion object : KSerializer<ResultMultiSearch> {

        override fun deserialize(decoder: Decoder): ResultMultiSearch {
            return try {
                Hits(decoder.decodeSerializableValue(ResponseSearch.serializer()))
            } catch (e: Exception) {
                Facets(decoder.decodeSerializableValue(ResponseSearchForFacets.serializer()))
            }
        }

        override val descriptor: SerialDescriptor = buildClassSerialDescriptor(ResultMultiSearch::class.qualifiedName!!)

        override fun serialize(encoder: Encoder, value: ResultMultiSearch) {
            throw UnsupportedOperationException("ResultMultiSearch serialization is not an expected operation")
        }
    }
}
