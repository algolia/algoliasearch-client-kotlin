package serialize

import com.algolia.search.saas.data.MatchLevel
import com.algolia.search.saas.data.MatchLevel.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestMatchLevel : TestSerializer<MatchLevel>(MatchLevel) {

    override val items = listOf(
        None,
        Partial,
        Full,
        Unknown(unknown)
    )
}