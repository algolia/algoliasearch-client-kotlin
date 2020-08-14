package com.algolia.search.model.rule

import com.algolia.search.model.Raw
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.KeyContains
import com.algolia.search.serialize.KeyEndsWith
import com.algolia.search.serialize.KeyIs
import com.algolia.search.serialize.KeyStartsWith
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

@Serializable(Anchoring.Companion::class)
public sealed class Anchoring(override val raw: String) : Raw<String> {

    /**
     * The [Pattern] matches the [Query.query].
     */
    public object Is : Anchoring(KeyIs)

    /**
     * The [Pattern] matches the beginning of the [Query.query].
     */
    public object StartsWith : Anchoring(KeyStartsWith)

    /**
     * The [Pattern] matches the beginning of the [Query.query].
     */
    public object EndsWith : Anchoring(KeyEndsWith)

    /**
     * The [Pattern] is contained by the [Query.query].
     */
    public object Contains : Anchoring(KeyContains)

    public data class Other(override val raw: String) : Anchoring(raw)

    public companion object : KSerializer<Anchoring> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: Anchoring) {
            serializer.serialize(encoder, value.raw)
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
