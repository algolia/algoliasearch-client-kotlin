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
import kotlinx.serialization.internal.StringSerializer


@Serializable(ABTestStatus.Companion::class)
public sealed class ABTestStatus(override val raw: String) : Raw<String> {

    public object Active : ABTestStatus(KeyActive)

    public object Stopped : ABTestStatus(KeyStopped)

    public object Expired : ABTestStatus(KeyExpired)

    public object Failed : ABTestStatus(KeyFailed)

    public data class Other(override val raw: String) : ABTestStatus(raw)

    internal companion object : KSerializer<ABTestStatus> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: ABTestStatus) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): ABTestStatus {
            val string = serializer.deserialize(decoder)

            return when (string) {
                KeyActive -> Active
                KeyStopped -> Stopped
                KeyExpired -> Expired
                KeyFailed -> Failed
                else -> Other(string)
            }
        }
    }
}