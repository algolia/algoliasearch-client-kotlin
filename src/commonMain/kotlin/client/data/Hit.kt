package client.data

import kotlinx.serialization.Decoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.JSON


@Serializable
data class Hit(val serialized: String) {

    @Serializer(forClass = Hit::class)
    companion object : KSerializer<Hit> {
        override fun deserialize(input: Decoder): Hit {
            val json = (input as JSON.JsonInput).readAsTree()
            return Hit(json.toString())
        }
    }
}