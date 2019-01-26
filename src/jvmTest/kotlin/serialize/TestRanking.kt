package serialize

import attributeA
import attributeB
import com.algolia.search.saas.model.Ranking
import com.algolia.search.saas.model.Ranking.*
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestRanking : TestSerializer<Ranking>(Ranking) {

    override val items = listOf(
        Geo to JsonLiteral(Geo.raw),
        Typo to JsonLiteral(Typo.raw),
        Words to JsonLiteral(Words.raw),
        Filters to JsonLiteral(Filters.raw),
        Proximity to JsonLiteral(Proximity.raw),
        Attribute to JsonLiteral(Attribute.raw),
        Exact to JsonLiteral(Exact.raw),
        Custom to JsonLiteral(Custom.raw),
        Asc(attributeA) to JsonLiteral(Asc(attributeA).raw),
        Desc(attributeB) to JsonLiteral(Desc(attributeB).raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}