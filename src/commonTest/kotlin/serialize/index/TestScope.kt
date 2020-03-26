package serialize.index

import com.algolia.search.model.index.Scope
import com.algolia.search.model.index.Scope.*
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown


internal class TestScope : TestSerializer<Scope>(Scope) {

    override val items = listOf(
        Rules to JsonLiteral(Rules.raw),
        Settings to JsonLiteral(Settings.raw),
        Synonyms to JsonLiteral(Synonyms.raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}