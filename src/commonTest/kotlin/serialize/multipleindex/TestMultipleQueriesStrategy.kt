package serialize.multipleindex

import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy.*
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown


internal class TestMultipleQueriesStrategy : TestSerializer<MultipleQueriesStrategy>(
    MultipleQueriesStrategy
) {

    override val items = listOf(
        None to JsonLiteral(None.raw),
        StopIfEnoughMatches to JsonLiteral(StopIfEnoughMatches.raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}