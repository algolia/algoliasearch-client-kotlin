package serialize

import com.algolia.search.saas.model.enums.QueryType
import com.algolia.search.saas.model.enums.QueryType.*
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestQueryType : TestSerializer<QueryType>(QueryType) {

    override val items = listOf(
        PrefixNone to JsonLiteral(PrefixNone.raw),
        PrefixAll to JsonLiteral(PrefixAll.raw),
        PrefixLast to JsonLiteral(PrefixLast.raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}