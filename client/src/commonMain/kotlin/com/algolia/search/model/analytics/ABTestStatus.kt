package com.algolia.search.model.analytics

import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * [ABTest] server-side status.
 */
@Serializable(ABTestStatus.Companion::class)
public sealed class ABTestStatus(override val raw: String) : Raw<String> {

    /**
     * The Analytics created the [ABTest] and performed a successful request to the engine.
     */
    public object Active : ABTestStatus(Key.Active)

    /**
     * The [ABTest] was stopped by a user: it was deleted from the engine but we have to keep the data for
     * historical purposes.
     */
    public object Stopped : ABTestStatus(Key.Stopped)

    /**
     * The [ABTest] reached its end date and was automatically stopped. It is removed from the engine but the
     * metadata/metrics are kept.
     */
    public object Expired : ABTestStatus(Key.Expired)

    /**
     * The [ABTest] creation failed.
     */
    public object Failed : ABTestStatus(Key.Failed)

    public data class Other(override val raw: String) : ABTestStatus(raw)

    public companion object : KSerializer<ABTestStatus> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: ABTestStatus) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): ABTestStatus {
            return when (val string = serializer.deserialize(decoder)) {
                Key.Active -> Active
                Key.Stopped -> Stopped
                Key.Expired -> Expired
                Key.Failed -> Failed
                else -> Other(string)
            }
        }
    }
}
