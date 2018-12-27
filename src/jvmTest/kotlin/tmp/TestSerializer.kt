package tmp

import client.serialize.Serializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import org.junit.Test
import kotlin.test.assertEquals


internal abstract class TestSerializer<T> {

    abstract val serializer: Serializer<T>

    abstract val item: List<Pair<T, JsonElement>>
    abstract val items: List<Pair<List<T>, JsonArray>>


    @Test
    fun serializeNull() {
        assertEquals(JsonNull, serializer.serialize(null))
        assertEquals(JsonNull, serializer.serializes(null))
    }

    @Test
    fun serialize() {
        item.forEach {
            assertEquals(it.second, serializer.serialize(it.first))
        }
    }

    @Test
    fun serializes() {
        items.forEach {
            assertEquals(it.second, serializer.serializes(it.first))
        }
    }

    @Test
    fun deserializeNull() {
        assertEquals(null, serializer.deserialize(JsonNull))
        assertEquals(null, serializer.deserializes(JsonNull))
    }

    @Test
    fun deserialize() {
        item.forEach {
            assertEquals(it.first, serializer.deserialize(it.second))
        }
    }

    @Test
    fun deserializes() {
        items.forEach {
            assertEquals(it.first, serializer.deserializes(it.second))
        }
    }
}