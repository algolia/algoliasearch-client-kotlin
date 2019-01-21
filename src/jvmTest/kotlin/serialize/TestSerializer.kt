package serialize

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import org.junit.Test
import shouldEqual


internal abstract class TestSerializer<T>(
    private val serializer: KSerializer<T>
) {

    abstract val items: List<Pair<T, JsonElement>>

    @Test
    fun serialize() {
        items.forEach {
            val serialized = Json.stringify(serializer, it.first)
            val deserialized = Json.parse(serializer, serialized)
            val element = Json.nonstrict.parseJson(serialized)

            deserialized shouldEqual it.first
            element shouldEqual it.second
        }
    }
}