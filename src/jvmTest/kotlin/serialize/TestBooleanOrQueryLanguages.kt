package serialize

import boolean
import client.data.BooleanOrQueryLanguages
import client.data.QueryLanguage
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import tmp.TestSerializer


@RunWith(JUnit4::class)
internal class TestBooleanOrQueryLanguages : TestSerializer<BooleanOrQueryLanguages>() {

    override val serializer = BooleanOrQueryLanguages
    override val item = listOf(
        BooleanOrQueryLanguages.Boolean(boolean) to JsonPrimitive(boolean),
        BooleanOrQueryLanguages.QueryLanguages(
            QueryLanguage.Afrikaans,
            QueryLanguage.Albanian
        ) to jsonArray {
            +QueryLanguage.Afrikaans.raw
            +QueryLanguage.Albanian.raw
        }
    )
    override val items: List<Pair<List<BooleanOrQueryLanguages>, JsonArray>> = listOf()
}