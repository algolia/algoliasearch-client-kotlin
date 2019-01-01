package client.data

import client.serialize.readAsTree
import kotlinx.serialization.Decoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer


@Serializable
data class Hit(val serialized: String) {

    @Serializer(Hit::class)
    companion object : KSerializer<Hit> {
        override fun deserialize(input: Decoder): Hit {
            val json = input.readAsTree()

            return Hit(json.toString())
        }
    }
}