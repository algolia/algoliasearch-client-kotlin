package serialize.multipleindex

import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy.None
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy.Other
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy.StopIfEnoughMatches
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestMultipleQueriesStrategy : TestSerializer<MultipleQueriesStrategy>(
    MultipleQueriesStrategy
) {

    override val items = listOf(
        None to JsonPrimitive(None.raw),
        StopIfEnoughMatches to JsonPrimitive(StopIfEnoughMatches.raw),
        Other(unknown) to JsonPrimitive(unknown)
    )
}
