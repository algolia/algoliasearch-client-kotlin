package serialize

import com.algolia.search.saas.data.FacetHits
import com.algolia.search.saas.serialize.KSerializerMapAny
import kotlinx.serialization.json.Json
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
        val serialized = Json.stringify(KSerializerMapAny, map)

        Json.nonstrict.parseJson(serialized) shouldEqual json {
            "string" to "string"
        }
    }

    @Test
    fun boolean() {
        val map = mapOf("boolean" to true)
        val serialized = Json.stringify(KSerializerMapAny, map)

        Json.nonstrict.parseJson(serialized) shouldEqual json {
            "boolean" to true
        }
    }

    @Test
    fun number() {
        val map = mapOf(
            "int" to 0,
            "long" to 0L,
            "float" to 0f,
            "double" to 0.0
        )
        val serialized = Json.stringify(KSerializerMapAny, map)

        Json.nonstrict.parseJson(serialized) shouldEqual json {
            "int" to 0
            "long" to 0L
            "float" to 0f
            "double" to 0.0
        }
    }

    @Test
    fun list() {
        val map = mapOf("list" to listOf("string", 0, true, listOf(0)))
        val serialized = Json.stringify(KSerializerMapAny, map)

        Json.nonstrict.parseJson(serialized) shouldEqual json {
            "list" to jsonArray {
                +"string"
                +(0 as Number)
                +true
                +jsonArray { +(0 as Number) }
            }
        }
    }

    @Test
    fun array() {
        val map = mapOf("list" to arrayOf("string", 0, true, arrayOf(0)))
        val serialized = Json.stringify(KSerializerMapAny, map)

        Json.nonstrict.parseJson(serialized) shouldEqual json {
            "list" to jsonArray {
                +"string"
                +(0 as Number)
                +true
                +jsonArray { +(0 as Number) }
            }
        }
    }

    @Test
    fun objects() {
        val someObject = FacetHits.Item("name", "highlight", 0)
        val map = mapOf("objects" to someObject)
        val serialized = Json.stringify(KSerializerMapAny, map)

        Json.nonstrict.parseJson(serialized) shouldEqual json {
            "objects" to someObject.toString()
        }
    }

    @Test
    fun map() {
        val map = mapOf("map" to mapOf("string" to "string", "int" to 0, "boolean" to true))
        val serialized = Json.stringify(KSerializerMapAny, map)

        Json.nonstrict.parseJson(serialized) shouldEqual json {
            "map" to json {
                "string" to "string"
                "int" to 0
                "boolean" to true
            }
        }
    }
}