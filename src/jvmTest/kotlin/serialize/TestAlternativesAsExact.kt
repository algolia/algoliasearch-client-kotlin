package serialize

import client.data.AlternativesAsExact
import client.data.AlternativesAsExact.*
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import tmp.TestSerializer
import unknown

@RunWith(JUnit4::class)
internal class TestAlternativesAsExact : TestSerializer<AlternativesAsExact>(AlternativesAsExact) {

    override val item = listOf(
        IgnorePlurals to JsonPrimitive(IgnorePlurals.raw),
        SingleWordSynonym to JsonPrimitive(SingleWordSynonym.raw),
        MultiWordsSynonym to JsonPrimitive(MultiWordsSynonym.raw),
        Unknown(unknown) to JsonPrimitive(unknown)
    )
    override val items = listOf(
        listOf(
            IgnorePlurals,
            SingleWordSynonym,
            MultiWordsSynonym,
            Unknown(unknown)
        ) to jsonArray {
            +IgnorePlurals.raw
            +SingleWordSynonym.raw
            +MultiWordsSynonym.raw
            +unknown
        }
    )
}