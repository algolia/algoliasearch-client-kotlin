package model.search

import attributeA
import attributeB
import com.algolia.search.model.settings.CustomRanking.Asc
import com.algolia.search.model.settings.CustomRanking.Desc
import com.algolia.search.serialize.KeyAsc
import com.algolia.search.serialize.KeyDesc
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestCustomRankingCriteria {

    @Test
    fun raw() {
        "$KeyAsc($attributeA)" shouldEqual Asc(attributeA).raw
        "$KeyDesc($attributeB)" shouldEqual Desc(attributeB).raw
    }
}