package data

import client.data.AroundRadius
import client.serialize.KeyAll
import kotlinx.serialization.json.JsonPrimitive
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestAroundRadius : TestSerializer<AroundRadius> {

    override val serializer = AroundRadius

    @Test
    fun key() {
        assertEquals("all", KeyAll)
    }

    @Test
    fun raw() {
        assertEquals(KeyAll, AroundRadius.All.raw)
        assertEquals("10", AroundRadius.InMeters(10).raw)
        assertEquals(unknown, AroundRadius.Unknown(unknown).raw)
    }

    @Test
    override fun serialize() {
        testSerialize(JsonPrimitive(KeyAll), AroundRadius.All)
        testSerialize(JsonPrimitive(10), AroundRadius.InMeters(10))
        testSerialize(JsonPrimitive(unknown), AroundRadius.Unknown(unknown))
        testSerializeNull()
    }

    @Test
    override fun deserialize() {
        testDeserialize(AroundRadius.All, JsonPrimitive(KeyAll))
        testDeserialize(AroundRadius.InMeters(10), JsonPrimitive(10))
        testDeserialize(AroundRadius.Unknown(unknown), JsonPrimitive(unknown))
        testDeserializeNull()
    }
}