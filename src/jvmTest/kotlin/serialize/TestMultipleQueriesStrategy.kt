package serialize

import com.algolia.search.saas.data.MultipleQueriesStrategy
import com.algolia.search.saas.data.MultipleQueriesStrategy.*
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestMultipleQueriesStrategy : TestSerializer<MultipleQueriesStrategy>(MultipleQueriesStrategy) {

    override val item = listOf(
        None to JsonPrimitive(None.raw),
        StopIfEnoughMatches to JsonPrimitive(StopIfEnoughMatches.raw),
        Unknown(unknown) to JsonPrimitive(unknown)
    )
}