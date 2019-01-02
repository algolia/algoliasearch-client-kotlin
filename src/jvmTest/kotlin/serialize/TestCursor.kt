package serialize

import com.algolia.search.saas.data.Cursor
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestCursor : TestSerializer<Cursor>(Cursor) {

    override val items = listOf(
        Cursor("cursor")
    )
}