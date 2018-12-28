package tmp

import client.serialize.Deserializer
import client.serialize.Serializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import org.junit.Test
import kotlin.test.assertEquals


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
                assertEquals(it.second, serializer.serialize(it.first))
            }
        }
    }

    @Test
    fun serializes() {
        if (serializer != null) {
            items.forEach {
                assertEquals(it.second, serializer.serializes(it.first))
            }
        }
    }

    @Test
    fun deserializeNull() {
        if (deserializer != null) {
            assertEquals(null, deserializer.deserialize(JsonNull))
            assertEquals(null, deserializer.deserializes(JsonNull))
        }
    }

    @Test
    fun deserialize() {
        if (deserializer != null) {
            item.forEach {
                assertEquals(it.first, deserializer.deserialize(it.second))
            }
        }
    }

    @Test
    fun deserializes() {
        if (deserializer != null) {
            items.forEach {
                assertEquals(it.first, deserializer.deserializes(it.second))
            }
        }
    }
}