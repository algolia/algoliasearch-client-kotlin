package com.algolia.search.model.multicluster

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.helper.toClusterName
import com.algolia.search.model.Raw
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(ClusterName.Companion::class)
public data class ClusterName(override val raw: String) : Raw<String> {

    init {
        if (raw.isBlank()) throw EmptyStringException("ClusterName")
    }

    override fun toString(): String {
        return raw
    }

    companion object : KSerializer<ClusterName> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: ClusterName) {
            StringSerializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): ClusterName {
            return serializer.deserialize(decoder).toClusterName()
        }
    }
}