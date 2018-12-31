import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


sealed class Primitive {

    data class String(val raw: kotlin.String) : Primitive()

    data class Number(val raw: kotlin.Number) : Primitive()

    data class Boolean(val raw: kotlin.Boolean) : Primitive()

    data class List(val raw: kotlin.collections.List<Primitive>) : Primitive()

    data class Object(val raw: JsonObject) : Primitive()

    object Null : Primitive()

    @Serializer(Primitive::class)
    companion object {

        private fun deserialize(json: JsonElement): Primitive {
            return when (json) {
                is JsonNull -> Null
                is JsonObject -> Object(json)
                is JsonArray -> List(json.map(::deserialize))
                is JsonLiteral -> {
                    when {
                        json.intOrNull != null -> Number(json.int)
                        json.booleanOrNull != null -> Boolean(json.boolean)
                        else -> String(json.content)
                    }
                }
            }
        }

        private fun serialize(obj: Primitive): JsonElement {
            return when (obj) {
                is String -> JsonLiteral(obj.raw)
                is Number -> JsonLiteral(obj.raw)
                is Boolean -> JsonLiteral(obj.raw)
                is List -> JsonArray(obj.raw.map(::serialize))
                is Object -> obj.raw
                is Null -> JsonNull
            }
        }

        override fun serialize(output: Encoder, obj: Primitive) {
            val json = output as JSON.JsonOutput

            return json.writeTree(serialize(obj))
        }

        override fun deserialize(input: Decoder): Primitive {
            val json = (input as JSON.JsonInput).readAsTree()

            return deserialize(json)
        }
    }
}

@Serializable
data class Obj(
    @Serializable(Primitive.Companion::class) val primitive: Primitive
)

@RunWith(JUnit4::class)
class Test {

    private val serializer = Obj.serializer()

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