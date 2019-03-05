package serialize.rule

import com.algolia.search.model.rule.*
import com.algolia.search.serialize.KeyEdits
import com.algolia.search.helper.toObjectID
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.json
import kotlinx.serialization.list
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer
import shouldEqual
import suite.loadScratch
import unknown


@RunWith(JUnit4::class)
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
        val rule = Json.parse(Rule.serializer(), loadScratch("rule_v1.json").readText())

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