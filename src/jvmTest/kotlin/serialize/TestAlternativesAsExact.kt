package serialize

import client.data.AlternativesAsExact
import client.data.AlternativesAsExact.*
import client.serialize.KeyIgnorePlurals
import client.serialize.KeyMultiWordsSynonym
import client.serialize.KeySingleWordSynonym
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import tmp.TestSerializer
import unknown

@RunWith(JUnit4::class)
internal class TestAlternativesAsExact: TestSerializer<AlternativesAsExact>() {

    override val serializer = Companion

    override val item = listOf(
        IgnorePlurals to JsonPrimitive(KeyIgnorePlurals),
        SingleWordSynonym to JsonPrimitive(KeySingleWordSynonym),
        MultiWordsSynonym to JsonPrimitive(KeyMultiWordsSynonym),
        Unknown(unknown) to JsonPrimitive(unknown)
    )
    override val items = listOf(
        listOf(
            IgnorePlurals,
            SingleWordSynonym,
            MultiWordsSynonym,
            Unknown(unknown)
        ) to jsonArray {
            +KeyIgnorePlurals
            +KeySingleWordSynonym
            +KeyMultiWordsSynonym
            +unknown
        }
    )
}