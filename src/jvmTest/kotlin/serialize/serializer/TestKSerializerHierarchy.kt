package serialize.serializer

import com.algolia.search.serialize.KSerializerHierarchy
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestKSerializerHierarchy {

    @Test
    fun test() {
        val serialized = Json.plain.stringify(KSerializerHierarchy, item)
        val deserialized = Json.plain.parse(KSerializerHierarchy, serialized)

        deserialized shouldEqual item
    }

    companion object {

        val item = mapOf(
            "lvl0" to listOf("products"),
            "lvl1" to listOf("products > handbags", "products > luxury"),
            "lvl2" to listOf("products > handbags > modern")
        )

        val json = json {
            "lvl0" to "products"
            "lvl1" to jsonArray {
                +"products > handbags"
                +"products > luxury"
            }
            "lvl2" to jsonArray {
                +"products > handbags > modern"
            }
        }
    }
}