package serialize

import com.algolia.search.saas.data.FacetHits
import com.algolia.search.saas.serialize.KSerializerMapAny
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestMapAny {

    @Test
    fun string() {
        val map = mapOf("string" to "string")
        val serialized = JSON.stringify(KSerializerMapAny, map)

        serialized shouldEqual json {
            "string" to "string"
        }.toString()
    }

    @Test
    fun boolean() {
        val map = mapOf("boolean" to true)
        val serialized = JSON.stringify(KSerializerMapAny, map)

        serialized shouldEqual json {
            "boolean" to true
        }.toString()
    }

    @Test
    fun number() {
        val map = mapOf(
            "int" to 0,
            "long" to 0L,
            "float" to 0f,
            "double" to 0.0
        )
        val serialized = JSON.stringify(KSerializerMapAny, map)

        serialized shouldEqual json {
            "int" to 0
            "long" to 0L
            "float" to 0f
            "double" to 0.0
        }.toString()
    }

    @Test
    fun list() {
        val map = mapOf("list" to listOf("string", 0, true, listOf(0)))
        val serialized = JSON.stringify(KSerializerMapAny, map)

        serialized shouldEqual json {
            "list" to jsonArray {
                +"string"
                +(0 as Number)
                +true
                +jsonArray { +(0 as Number) }
            }
        }.toString()
    }

    @Test
    fun array() {
        val map = mapOf("list" to arrayOf("string", 0, true, arrayOf(0)))
        val serialized = JSON.stringify(KSerializerMapAny, map)

        serialized shouldEqual json {
            "list" to jsonArray {
                +"string"
                +(0 as Number)
                +true
                +jsonArray { +(0 as Number) }
            }
        }.toString()
    }

    @Test
    fun objects() {
        val someObject = FacetHits.Item("name", "highlight", 0)
        val map = mapOf("objects" to someObject)
        val serialized = JSON.stringify(KSerializerMapAny, map)

        serialized shouldEqual json {
            "objects" to someObject.toString()
        }.toString()
    }

    @Test
    fun map() {
        val map = mapOf("map" to mapOf("string" to "string", "int" to 0, "boolean" to true))
        val serialized = JSON.stringify(KSerializerMapAny, map)

        serialized shouldEqual json {
            "map" to json {
                "string" to "string"
                "int" to 0
                "boolean" to true
            }
        }.toString()
    }
}