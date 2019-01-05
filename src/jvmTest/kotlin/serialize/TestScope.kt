package serialize

import com.algolia.search.saas.data.Scope
import com.algolia.search.saas.data.Scope.*
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestScope : TestSerializer<Scope>(Scope) {

    override val items = listOf(
        Rules to JsonLiteral(Rules.raw),
        Settings to JsonLiteral(Settings.raw),
        Synonyms to JsonLiteral(Synonyms.raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}