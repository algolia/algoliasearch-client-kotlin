package serialize

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JSON
import org.junit.Test
import shouldEqual


internal abstract class TestSerializer<T>(
    private val serializer: KSerializer<T>
) {

    abstract val items: List<T>

    @Test
    fun serialize() {
        items.forEach {
            val serialized = JSON.stringify(serializer, it)
            val deserialized = JSON.parse(serializer, serialized)

            deserialized shouldEqual it
        }
    }
}