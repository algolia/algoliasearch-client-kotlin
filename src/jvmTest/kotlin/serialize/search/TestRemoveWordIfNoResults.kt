package serialize.search

import com.algolia.search.model.search.RemoveWordIfNoResults
import com.algolia.search.model.search.RemoveWordIfNoResults.*
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer
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