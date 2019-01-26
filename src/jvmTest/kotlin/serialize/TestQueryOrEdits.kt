package serialize

import com.algolia.search.saas.model.query_rule.Edit
import com.algolia.search.saas.model.query_rule.QueryOrEdits
import com.algolia.search.saas.serialize.KeyEdits
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.json
import kotlinx.serialization.list
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
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
}