package serialize

import attributeA
import attributeB
import com.algolia.search.saas.data.Ranking
import com.algolia.search.saas.data.Ranking.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestRanking : TestSerializer<Ranking>(Ranking) {

    override val items = listOf(
        Geo,
        Typo,
        Words,
        Filters,
        Proximity,
        Attribute,
        Exact,
        Custom,
        Asc(attributeA),
        Desc(attributeB),
        Other(unknown)
    )
}