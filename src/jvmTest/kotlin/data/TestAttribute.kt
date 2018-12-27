package data

import attributeA
import attributeB
import client.data.Attribute
import client.toAttribute
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestAttribute : TestSerializer<Attribute> {

    override val serializer = Attribute

    @Test
    fun dx() {
        assertEquals(Attribute("attributeA"), "attributeA".toAttribute())
    }

    @Test
    fun raw() {
        assertEquals("raw", Attribute("raw").name)
    }

    @Test
    override fun serialize() {
        testSerialize(JsonPrimitive(attributeA.name), attributeA)
        testSerializeNull()
        testSerializeArray(
            jsonArray {
                +attributeA.name
                +attributeB.name
            },
            listOf(
                attributeA,
                attributeB
            )
        )
    }

    @Test
    override fun deserialize() {
        testDeserialize(attributeA, JsonPrimitive(attributeA.name))
        testDeserializeNull()
        testDeserializeArray(
            listOf(
                attributeA,
                attributeB
            ),
            jsonArray {
                +attributeA.name
                +attributeB.name
            }
        )
    }
}