package serialize

import com.algolia.search.saas.data.RemoveWordIfNoResults
import com.algolia.search.saas.data.RemoveWordIfNoResults.*
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestRemoveWordIfNoResults : TestSerializer<RemoveWordIfNoResults>(RemoveWordIfNoResults) {

    override val item = listOf(
        None to JsonPrimitive(None.raw),
        LastWords to JsonPrimitive(LastWords.raw),
        FirstWords to JsonPrimitive(FirstWords.raw),
        AllOptional to JsonPrimitive(AllOptional.raw),
        Unknown(unknown) to JsonPrimitive(unknown)
    )
}