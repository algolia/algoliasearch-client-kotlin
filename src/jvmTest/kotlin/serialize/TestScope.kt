package serialize

import com.algolia.search.saas.data.Scope
import com.algolia.search.saas.data.Scope.*
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestScope : TestSerializer<Scope>(Scope) {

    override val item = listOf(
        Rules to JsonPrimitive(Rules.raw),
        Settings to JsonPrimitive(Settings.raw),
        Synonyms to JsonPrimitive(Synonyms.raw),
        Unknown(unknown) to JsonPrimitive(unknown)

    )
}