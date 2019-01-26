package serialize

import com.algolia.search.saas.model.multiple_index.MultipleQueriesStrategy
import com.algolia.search.saas.model.multiple_index.MultipleQueriesStrategy.*
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestMultipleQueriesStrategy : TestSerializer<MultipleQueriesStrategy>(
    MultipleQueriesStrategy
) {

    override val items = listOf(
        None to JsonLiteral(None.raw),
        StopIfEnoughMatches to JsonLiteral(StopIfEnoughMatches.raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}