package serialize

import com.algolia.search.saas.serialize.Primitive
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestPrimitive {

    @Serializable
    internal data class Obj(
        @Serializable(Primitive.Companion::class) val primitive: Primitive
    )

    @Serializable
    internal data class Objs(
        val primitives: List<Primitive>
    )

    private val serializer = Obj.serializer()

    @Test
    fun stuff() {
        val obj = Objs(listOf(Primitive.String("hello")))
        val serialized = JSON.stringify(Objs.serializer(), obj)
        val deserialized = JSON.parse(Objs.serializer(), serialized)

        obj shouldEqual deserialized
    }

    @Test
    fun number() {
        val obj = Obj(Primitive.Number(0))
        val serialized = JSON.stringify(serializer, obj)
        val deserialized = JSON.parse(serializer, serialized)

        "{\"primitive\":0}" shouldEqual serialized
        obj shouldEqual deserialized
    }

    @Test
    fun boolean() {
        val obj = Obj(Primitive.Boolean(true))
        val serialized = JSON.stringify(serializer, obj)
        val deserialized = JSON.parse(serializer, serialized)

        "{\"primitive\":true}" shouldEqual serialized
        obj shouldEqual deserialized
    }

    @Test
    fun string() {
        val obj = Obj(Primitive.String("string"))
        val serialized = JSON.stringify(serializer, obj)
        val deserialized = JSON.parse(serializer, serialized)

        "{\"primitive\":\"string\"}" shouldEqual serialized
        obj shouldEqual deserialized
    }

    @Test
    fun nullable() {
        val obj = Obj(Primitive.Null)
        val serialized = JSON.stringify(serializer, obj)
        val deserialized = JSON.parse(serializer, serialized)

        "{\"primitive\":null}" shouldEqual serialized
        obj shouldEqual deserialized
    }


    @Test
    fun list() {
        val obj = Obj(Primitive.List(listOf(Primitive.String("string"))))
        val serialized = JSON.stringify(serializer, obj)
        val deserialized = JSON.parse(serializer, serialized)

        "{\"primitive\":[\"string\"]}" shouldEqual serialized
        obj shouldEqual deserialized
    }

    @Test
    fun objects() {
        val obj = Obj(Primitive.Object(JsonObject(mapOf("nested" to JsonLiteral("string")))))
        val serialized = JSON.stringify(serializer, obj)
        val deserialized = JSON.parse(serializer, serialized)

        "{\"primitive\":{\"nested\": \"string\"}}" shouldEqual serialized
        obj shouldEqual deserialized
    }
}