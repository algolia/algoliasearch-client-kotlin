package serialize

import com.algolia.search.saas.data.QueryType
import com.algolia.search.saas.data.QueryType.*
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestQueryType : TestSerializer<QueryType>(QueryType) {

    override val item = listOf(
        PrefixNone to JsonPrimitive(PrefixNone.raw),
        PrefixAll to JsonPrimitive(PrefixAll.raw),
        PrefixLast to JsonPrimitive(PrefixLast.raw),
        Unknown(unknown) to JsonPrimitive(unknown)
    )
}