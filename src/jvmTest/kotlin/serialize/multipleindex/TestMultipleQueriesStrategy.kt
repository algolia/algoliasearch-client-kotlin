package serialize.multipleindex

import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy.*
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer
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