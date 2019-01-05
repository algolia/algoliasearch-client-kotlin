package serialize

import com.algolia.search.saas.data.MultipleQueriesStrategy
import com.algolia.search.saas.data.MultipleQueriesStrategy.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestMultipleQueriesStrategy : TestSerializer<MultipleQueriesStrategy>(MultipleQueriesStrategy) {

    override val items = listOf(
        None,
        StopIfEnoughMatches,
        Other(unknown)
    )
}