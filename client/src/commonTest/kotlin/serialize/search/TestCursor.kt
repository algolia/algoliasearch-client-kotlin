package serialize.search

import com.algolia.search.model.search.Cursor
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer

internal class TestCursor : TestSerializer<Cursor>(Cursor) {

    override val items = listOf(
        Cursor("cursor") to JsonPrimitive("cursor")
    )
}
