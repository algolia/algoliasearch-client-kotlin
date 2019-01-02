package serialize

import com.algolia.search.saas.data.AlternativesAsExact
import com.algolia.search.saas.data.AlternativesAsExact.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown

@RunWith(JUnit4::class)
internal class TestAlternativesAsExact : TestSerializer<AlternativesAsExact>(AlternativesAsExact) {

    override val items = listOf(
        IgnorePlurals,
        SingleWordSynonym,
        MultiWordsSynonym,
        Unknown(unknown)
    )
}