package serialize.index

import com.algolia.search.model.index.Scope
import com.algolia.search.model.index.Scope.Other
import com.algolia.search.model.index.Scope.Rules
import com.algolia.search.model.index.Scope.Settings
import com.algolia.search.model.index.Scope.Synonyms
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestScope : TestSerializer<Scope>(Scope) {

    override val items = listOf(
        Rules to JsonPrimitive(Rules.raw),
        Settings to JsonPrimitive(Settings.raw),
        Synonyms to JsonPrimitive(Synonyms.raw),
        Other(unknown) to JsonPrimitive(unknown)
    )
}
