package serialize.rule

import com.algolia.search.helper.toObjectID
import com.algolia.search.model.rule.*
import com.algolia.search.serialize.KeyEdits
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.json
import kotlinx.serialization.list
import loadScratch
import kotlin.test.Test
import serialize.TestSerializer
import shouldEqual
import unknown


internal class TestQueryOrEdits : TestSerializer<QueryOrEdits>(QueryOrEdits) {

    private val edits = listOf(
        Edit(unknown),
        Edit(unknown, unknown)
    )

    override val items = listOf(
        QueryOrEdits.Edits(edits) to json { KeyEdits to Json.plain.toJson(Edit.list, edits) },
        QueryOrEdits.Query(unknown) to JsonLiteral(unknown)
    )

    @Test
    fun backwardCompatibility() {
        val rule = Json.parse(Rule.serializer(), loadScratch("rule_v1.json"))

        rule shouldEqual Rule(
            "query_edits".toObjectID(),
            Condition(
                Pattern.Literal("mobile phone"),
                Anchoring.Is
            ),
            Consequence(
                Params(
                    query = QueryOrEdits.Edits(listOf(Edit("mobile"), Edit("phone")))
                )
            )
        )
    }
}