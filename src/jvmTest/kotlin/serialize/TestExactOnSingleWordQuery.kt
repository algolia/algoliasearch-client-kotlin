package serialize

import com.algolia.search.saas.data.ExactOnSingleWordQuery
import com.algolia.search.saas.data.ExactOnSingleWordQuery.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestExactOnSingleWordQuery : TestSerializer<ExactOnSingleWordQuery>(ExactOnSingleWordQuery) {

    override val items = listOf(
        Attribute,
        None,
        Word,
        Other(unknown)
    )
}