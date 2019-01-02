package serialize

import com.algolia.search.saas.data.QueryType
import com.algolia.search.saas.data.QueryType.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestQueryType : TestSerializer<QueryType>(QueryType) {

    override val items = listOf(
        PrefixNone,
        PrefixAll,
        PrefixLast,
        Unknown(unknown)
    )
}