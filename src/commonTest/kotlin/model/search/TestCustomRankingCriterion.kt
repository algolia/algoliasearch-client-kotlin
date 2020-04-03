package model.search

import attributeA
import attributeB
import com.algolia.search.model.settings.CustomRankingCriterion.Asc
import com.algolia.search.model.settings.CustomRankingCriterion.Desc
import com.algolia.search.serialize.KeyAsc
import com.algolia.search.serialize.KeyDesc
import shouldEqual
import kotlin.test.Test

internal class TestCustomRankingCriterion {

    @Test
    fun raw() {
        Asc(attributeA).raw shouldEqual "$KeyAsc($attributeA)"
        Desc(attributeB).raw shouldEqual "$KeyDesc($attributeB)"
    }
}
