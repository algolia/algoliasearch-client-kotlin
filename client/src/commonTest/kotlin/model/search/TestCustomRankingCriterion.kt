package model.search

import attributeA
import attributeB
import com.algolia.search.model.settings.CustomRankingCriterion.Asc
import com.algolia.search.model.settings.CustomRankingCriterion.Desc
import com.algolia.search.serialize.internal.Key
import shouldEqual
import kotlin.test.Test

internal class TestCustomRankingCriterion {

    @Test
    fun raw() {
        Asc(attributeA).raw shouldEqual "${Key.Asc}($attributeA)"
        Desc(attributeB).raw shouldEqual "${Key.Desc}($attributeB)"
    }
}
