package serialize

import com.algolia.search.saas.data.Scope
import com.algolia.search.saas.data.Scope.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestScope : TestSerializer<Scope>(Scope) {

    override val items = listOf(
        Rules,
        Settings,
        Synonyms,
        Other(unknown)

    )
}