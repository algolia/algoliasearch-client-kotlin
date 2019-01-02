package serialize

import com.algolia.search.saas.data.RemoveWordIfNoResults
import com.algolia.search.saas.data.RemoveWordIfNoResults.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestRemoveWordIfNoResults : TestSerializer<RemoveWordIfNoResults>(RemoveWordIfNoResults) {

    override val items = listOf(
        None,
        LastWords,
        FirstWords,
        AllOptional,
        Unknown(unknown)
    )
}