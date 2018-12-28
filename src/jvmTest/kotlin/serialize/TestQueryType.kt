package serialize

import client.data.QueryType
import client.data.QueryType.*
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestQueryType : TestSerializer<QueryType>(QueryType, QueryType) {

    override val item = listOf(
        PrefixNone to JsonPrimitive(PrefixNone.raw),
        PrefixAll to JsonPrimitive(PrefixAll.raw),
        PrefixLast to JsonPrimitive(PrefixLast.raw),
        Unknown(unknown) to JsonPrimitive(unknown)
    )
    override val items = listOf(
        listOf(
            PrefixNone,
            PrefixAll,
            PrefixLast,
            Unknown(unknown)
        ) to jsonArray {
            +PrefixNone.raw
            +PrefixAll.raw
            +PrefixLast.raw
            +unknown
        }
    )
}