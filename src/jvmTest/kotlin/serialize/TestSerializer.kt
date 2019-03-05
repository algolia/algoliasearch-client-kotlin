package serialize

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import org.junit.Test
import shouldEqual


internal abstract class TestSerializer<T>(
    private val serializer: KSerializer<T>,
    private val json: Json = Json.plain
) {

    abstract val items: List<Pair<T, JsonElement>>

    @Test
    fun serialize() {
        items.forEach {
            val serialized = json.stringify(serializer, it.first)
            val deserialized = json.parse(serializer, serialized)
            val deserializedJson = json.parseJson(serialized)

            deserialized shouldEqual it.first
            deserializedJson shouldEqual it.second
        }
    }
}