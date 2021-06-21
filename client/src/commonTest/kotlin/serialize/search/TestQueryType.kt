package serialize.search

import com.algolia.search.model.search.QueryType
import com.algolia.search.model.search.QueryType.Other
import com.algolia.search.model.search.QueryType.PrefixAll
import com.algolia.search.model.search.QueryType.PrefixLast
import com.algolia.search.model.search.QueryType.PrefixNone
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestQueryType : TestSerializer<QueryType>(QueryType) {

    override val items = listOf(
        PrefixNone to JsonPrimitive(PrefixNone.raw),
        PrefixAll to JsonPrimitive(PrefixAll.raw),
        PrefixLast to JsonPrimitive(PrefixLast.raw),
        Other(unknown) to JsonPrimitive(unknown)
    )
}
