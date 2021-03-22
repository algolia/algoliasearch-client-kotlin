package com.algolia.search.model.multicluster

import com.algolia.search.helper.toClusterName
import com.algolia.search.model.internal.Raw
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * [ClusterName] of a cluster.
 */
@Serializable(ClusterName.Companion::class)
public data class ClusterName(override val raw: String) : Raw<String> {

    override fun toString(): String {
        return raw
    }

    public companion object : KSerializer<ClusterName> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: ClusterName) {
            String.serializer().serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): ClusterName {
            return serializer.deserialize(decoder).toClusterName()
        }
    }
}
