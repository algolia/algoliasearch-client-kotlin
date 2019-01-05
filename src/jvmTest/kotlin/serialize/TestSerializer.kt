package serialize

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import org.junit.Test
import shouldEqual


internal abstract class TestSerializer<T>(
    private val serializer: KSerializer<T>
) {

    abstract val items: List<T>

    @Test
    fun serialize() {
        items.forEach {
            val serialized = Json.stringify(serializer, it)
            val deserialized = Json.parse(serializer, serialized)

            println(serialized)
            deserialized shouldEqual it
        }
    }
}