package serialize

import client.data.Scope
import client.data.Scope.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestScope : TestSerializer<Scope>(Scope, null) {

    override val item = listOf(
        Rules to JsonPrimitive(Rules.raw),
        Settings to JsonPrimitive(Settings.raw),
        Synonyms to JsonPrimitive(Synonyms.raw),
        Unknown(unknown) to JsonPrimitive(unknown)

    )
    override val items: List<Pair<List<Scope>, JsonArray>> = listOf(
        listOf(Rules, Settings) to jsonArray {
            +Rules.raw
            +Settings.raw
        }
    )
}