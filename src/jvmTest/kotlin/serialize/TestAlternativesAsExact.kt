package serialize

import com.algolia.search.saas.data.AlternativesAsExact
import com.algolia.search.saas.data.AlternativesAsExact.*
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown

@RunWith(JUnit4::class)
internal class TestAlternativesAsExact : TestSerializer<AlternativesAsExact>(AlternativesAsExact) {

    override val item = listOf(
        IgnorePlurals to JsonPrimitive(IgnorePlurals.raw),
        SingleWordSynonym to JsonPrimitive(SingleWordSynonym.raw),
        MultiWordsSynonym to JsonPrimitive(MultiWordsSynonym.raw),
        Unknown(unknown) to JsonPrimitive(unknown)
    )
}