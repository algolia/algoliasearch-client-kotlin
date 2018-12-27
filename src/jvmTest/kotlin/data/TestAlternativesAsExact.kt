package data

import client.data.AlternativesAsExact
import client.data.AlternativesAsExact.*
import client.serialize.KeyIgnorePlurals
import client.serialize.KeyMultiWordsSynonym
import client.serialize.KeySingleWordSynonym
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestAlternativesAsExact : TestSerializer<AlternativesAsExact> {

    override val serializer = AlternativesAsExact.Companion

    @Test
    fun key() {
        assertEquals("ignorePlurals", KeyIgnorePlurals)
        assertEquals("singleWordSynonym", KeySingleWordSynonym)
        assertEquals("multiWordsSynonym", KeyMultiWordsSynonym)
    }

    @Test
    fun raws() {
        assertEquals(KeyIgnorePlurals, IgnorePlurals.raw)
        assertEquals(KeySingleWordSynonym, SingleWordSynonym.raw)
        assertEquals(KeyMultiWordsSynonym, MultiWordsSynonym.raw)
        assertEquals(unknown, Unknown(unknown).raw)
    }

    @Test
    fun serialize() {
        testSerialize(JsonPrimitive(KeyIgnorePlurals), IgnorePlurals)
        testSerialize(JsonPrimitive(KeySingleWordSynonym), SingleWordSynonym)
        testSerialize(JsonPrimitive(KeyMultiWordsSynonym), MultiWordsSynonym)
        testSerialize(JsonPrimitive(unknown), Unknown(unknown))
        testSerializeNull()
        testSerializeArray(
            jsonArray {
                +KeyIgnorePlurals
                +KeySingleWordSynonym
                +KeyMultiWordsSynonym
                +unknown
            },
            listOf(
                IgnorePlurals,
                SingleWordSynonym,
                MultiWordsSynonym,
                Unknown(unknown)
            )
        )
    }

    @Test
    fun deserialize() {
        val jsonArray = jsonArray {
            +KeyIgnorePlurals
            +KeySingleWordSynonym
            +KeyMultiWordsSynonym
            +unknown
        }

        testDeserialize(IgnorePlurals, JsonPrimitive(KeyIgnorePlurals))
        testDeserialize(SingleWordSynonym, JsonPrimitive(KeySingleWordSynonym))
        testDeserialize(MultiWordsSynonym, JsonPrimitive(KeyMultiWordsSynonym))
        testDeserialize(Unknown(unknown), JsonPrimitive(unknown))
        testDeserializeNull()
        testDeserializeArray(
            listOf(
                IgnorePlurals,
                SingleWordSynonym,
                MultiWordsSynonym,
                Unknown(unknown)
            ),
            jsonArray
        )
    }
}