package serialize.search

import com.algolia.search.model.search.QueryType
import com.algolia.search.model.search.QueryType.*
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown


internal class TestQueryType : TestSerializer<QueryType>(QueryType) {

    override val items = listOf(
        PrefixNone to JsonLiteral(PrefixNone.raw),
        PrefixAll to JsonLiteral(PrefixAll.raw),
        PrefixLast to JsonLiteral(PrefixLast.raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}