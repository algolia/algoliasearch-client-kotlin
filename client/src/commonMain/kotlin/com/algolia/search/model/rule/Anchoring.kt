package com.algolia.search.model.rule

import com.algolia.search.model.internal.Raw
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(Anchoring.Companion::class)
public sealed class Anchoring(override val raw: String) : Raw<String> {

    /**
     * The [Pattern] matches the [Query.query].
     */
    public object Is : Anchoring(Key.Is)

    /**
     * The [Pattern] matches the beginning of the [Query.query].
     */
    public object StartsWith : Anchoring(Key.StartsWith)

    /**
     * The [Pattern] matches the beginning of the [Query.query].
     */
    public object EndsWith : Anchoring(Key.EndsWith)

    /**
     * The [Pattern] is contained by the [Query.query].
     */
    public object Contains : Anchoring(Key.Contains)

    public data class Other(override val raw: String) : Anchoring(raw)

    public companion object : KSerializer<Anchoring> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: Anchoring) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): Anchoring {
            return when (val string = serializer.deserialize(decoder)) {
                Key.Is -> Is
                Key.EndsWith -> EndsWith
                Key.StartsWith -> StartsWith
                Key.Contains -> Contains
                else -> Other(string)
            }
        }
    }
}
