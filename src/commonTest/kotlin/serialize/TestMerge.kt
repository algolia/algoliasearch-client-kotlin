package serialize

import com.algolia.search.serialize.merge
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.json
import kotlin.test.Test
import shouldContainKey
import shouldContainValue


internal class TestMerge {

    @Test
    fun merge() {
        val jsonA = json { "KeyA" to "valueA" }
        val jsonB = json { "KeyB" to "valueB" }
        val jsonC = jsonA.merge(jsonB)

        jsonC shouldContainKey "KeyA"
        jsonC shouldContainKey "KeyB"
        jsonC shouldContainValue JsonLiteral("valueA")
        jsonC shouldContainValue JsonLiteral("valueB")
    }
}