package data

import boolean
import client.data.BooleanOrQueryLanguages
import client.data.BooleanOrQueryLanguages.Boolean
import client.data.BooleanOrQueryLanguages.QueryLanguages
import client.data.QueryLanguage
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestBooleanOrQueryLanguages : TestSerializer<BooleanOrQueryLanguages> {

    override val serializer = BooleanOrQueryLanguages

    @Test
    override fun serialize() {
        testSerialize(JsonPrimitive(boolean), Boolean(boolean))
        testSerialize(
            jsonArray {
                +QueryLanguage.Afrikaans.raw
                +QueryLanguage.Albanian.raw
            },
            QueryLanguages(QueryLanguage.Afrikaans, QueryLanguage.Albanian)
        )
        testSerializeNull()
    }

    @Test
    override fun deserialize() {
        testDeserialize(Boolean(boolean), JsonPrimitive(boolean))
        testDeserialize(
            BooleanOrQueryLanguages.QueryLanguages(QueryLanguage.Afrikaans, QueryLanguage.Albanian),
            jsonArray {
                +QueryLanguage.Afrikaans.raw
                +QueryLanguage.Albanian.raw
            }
        )
        testDeserializeNull()
    }
}