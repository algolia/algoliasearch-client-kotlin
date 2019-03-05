package serialize.search

import com.algolia.search.model.search.Cursor
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer


@RunWith(JUnit4::class)
internal class TestCursor : TestSerializer<Cursor>(Cursor) {

    override val items = listOf(
        Cursor("cursor") to JsonLiteral("cursor")
    )
}