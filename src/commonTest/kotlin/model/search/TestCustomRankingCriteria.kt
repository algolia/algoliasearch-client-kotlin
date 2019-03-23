package model.search

import attributeA
import attributeB
import com.algolia.search.model.settings.CustomRankingCriteria.Asc
import com.algolia.search.model.settings.CustomRankingCriteria.Desc
import com.algolia.search.serialize.KeyAsc
import com.algolia.search.serialize.KeyDesc
import kotlin.test.Test
import shouldEqual


internal class TestCustomRankingCriteria {

    @Test
    fun raw() {
        Asc(attributeA).raw shouldEqual "$KeyAsc($attributeA)"
        Desc(attributeB).raw shouldEqual "$KeyDesc($attributeB)"
    }
}