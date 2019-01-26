package serialize

import com.algolia.search.saas.model.RemoveWordIfNoResults
import com.algolia.search.saas.model.RemoveWordIfNoResults.*
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestRemoveWordIfNoResults : TestSerializer<RemoveWordIfNoResults>(RemoveWordIfNoResults) {

    override val items = listOf(
        None to JsonLiteral(None.raw),
        LastWords to JsonLiteral(LastWords.raw),
        FirstWords to JsonLiteral(FirstWords.raw),
        AllOptional to JsonLiteral(AllOptional.raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}