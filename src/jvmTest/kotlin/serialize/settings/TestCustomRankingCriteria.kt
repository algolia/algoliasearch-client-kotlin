package serialize.settings

import attributeA
import attributeB
import com.algolia.search.model.settings.CustomRankingCriteria
import com.algolia.search.model.settings.CustomRankingCriteria.*
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer
import unknown


@RunWith(JUnit4::class)
internal class TestCustomRankingCriteria : TestSerializer<CustomRankingCriteria>(CustomRankingCriteria) {

    override val items = listOf(
        Asc(attributeA) to JsonLiteral(Asc(attributeA).raw),
        Desc(attributeB) to JsonLiteral(Desc(attributeB).raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}