package com.algolia.search.model.analytics

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyActive
import com.algolia.search.serialize.KeyExpired
import com.algolia.search.serialize.KeyFailed
import com.algolia.search.serialize.KeyStopped
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

/**
 * [ABTest] server-side status.
 */
@Serializable(ABTestStatus.Companion::class)
public sealed class ABTestStatus(override val raw: String) : Raw<String> {

    /**
     * The Analytics created the [ABTest] and performed a successful request to the engine.
     */
    public object Active : ABTestStatus(KeyActive)

    /**
     * The [ABTest] was stopped by a user: it was deleted from the engine but we have to keep the data for
     * historical purposes.
     */
    public object Stopped : ABTestStatus(KeyStopped)

    /**
     * The [ABTest] reached its end date and was automatically stopped. It is removed from the engine but the
     * metadata/metrics are kept.
     */
    public object Expired : ABTestStatus(KeyExpired)

    /**
     * The [ABTest] creation failed.
     */
    public object Failed : ABTestStatus(KeyFailed)

    public data class Other(override val raw: String) : ABTestStatus(raw)

    companion object : KSerializer<ABTestStatus> {

        private val serializer = String.serializer()

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: ABTestStatus) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): ABTestStatus {
            return when (val string = serializer.deserialize(decoder)) {
                KeyActive -> Active
                KeyStopped -> Stopped
                KeyExpired -> Expired
                KeyFailed -> Failed
                else -> Other(string)
            }
        }
    }
}
