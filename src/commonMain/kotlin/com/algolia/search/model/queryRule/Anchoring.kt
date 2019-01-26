package com.algolia.search.model.queryRule

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyContains
import com.algolia.search.serialize.KeyEndsWith
import com.algolia.search.serialize.KeyIs
import com.algolia.search.serialize.KeyStartsWith
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(Anchoring.Companion::class)
sealed class Anchoring(override val raw: String) : Raw<String> {

    object Is : Anchoring(KeyIs)

    object StartsWith : Anchoring(KeyStartsWith)

    object EndsWith : Anchoring(KeyEndsWith)

    object Contains : Anchoring(KeyContains)

    data class Other(override val raw: String) : Anchoring(raw)

    companion object : KSerializer<Anchoring> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: Anchoring) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): Anchoring {
            val string = serializer.deserialize(decoder)

            return when (string) {
                KeyIs -> Is
                KeyEndsWith -> EndsWith
                KeyStartsWith -> StartsWith
                KeyContains -> Contains
                else -> Other(string)
            }
        }
    }
}