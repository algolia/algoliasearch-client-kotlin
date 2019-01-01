package serialize

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.JsonElement
import org.junit.Test
import shouldEqual


internal abstract class TestSerializer<T>(
    private val serializer: KSerializer<T>
) {

    abstract val item: List<Pair<T, JsonElement>>

    @Test
    fun serialize() {
        item.forEach {
            val serialized = JSON.stringify(serializer, it.first)
            val deserialized = JSON.parse(serializer, serialized)

            deserialized shouldEqual it.first
        }
    }
}