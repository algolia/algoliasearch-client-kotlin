package com.algolia.search.model.rule

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
public sealed class Anchoring(override val raw: String) : Raw<String> {

    public object Is : Anchoring(KeyIs)

    public object StartsWith : Anchoring(KeyStartsWith)

    public object EndsWith : Anchoring(KeyEndsWith)

    public object Contains : Anchoring(KeyContains)

    public data class Other(override val raw: String) : Anchoring(raw)

    companion object : KSerializer<Anchoring> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: Anchoring) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): Anchoring {
            return when (val string = serializer.deserialize(decoder)) {
                KeyIs -> Is
                KeyEndsWith -> EndsWith
                KeyStartsWith -> StartsWith
                KeyContains -> Contains
                else -> Other(string)
            }
        }
    }
}