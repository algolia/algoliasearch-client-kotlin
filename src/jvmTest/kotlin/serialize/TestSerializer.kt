package serialize

import client.serialize.Deserializer
import client.serialize.Serializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import org.junit.Test
import shouldBeNull
import shouldEqual


internal abstract class TestSerializer<T>(
    private val serializer: Serializer<T>?,
    private val deserializer: Deserializer<T>?
) {

    abstract val item: List<Pair<T, JsonElement>>
    abstract val items: List<Pair<List<T>, JsonArray>>


    @Test
    fun serialize() {
        if (serializer != null) {
            item.forEach {
                it.second shouldEqual serializer.serialize(it.first)
            }
        }
    }

    @Test
    fun serializes() {
        if (serializer != null) {
            items.forEach {
                it.second shouldEqual serializer.serializeList(it.first)
            }
        }
    }

    @Test
    fun deserializeNull() {
        if (deserializer != null) {
            deserializer.deserialize(JsonNull).shouldBeNull()
            deserializer.deserializeList(JsonNull).shouldBeNull()
        }
    }

    @Test
    fun deserialize() {
        if (deserializer != null) {
            item.forEach {
                it.first shouldEqual deserializer.deserialize(it.second)
            }
        }
    }

    @Test
    fun deserializes() {
        if (deserializer != null) {
            items.forEach {
                it.first shouldEqual deserializer.deserializeList(it.second)
            }
        }
    }
}