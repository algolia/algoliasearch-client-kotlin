package serialize

import com.algolia.search.serialize.internal.merge
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import shouldContainKey
import shouldContainValue
import kotlin.test.Test

internal class TestMerge {

    @Test
    fun merge() {
        val jsonA = buildJsonObject { put("KeyA", "valueA") }
        val jsonB = buildJsonObject { put("KeyB", "valueB") }
        val jsonC = jsonA.merge(jsonB)

        jsonC shouldContainKey "KeyA"
        jsonC shouldContainKey "KeyB"
        jsonC shouldContainValue JsonPrimitive("valueA")
        jsonC shouldContainValue JsonPrimitive("valueB")
    }
}
