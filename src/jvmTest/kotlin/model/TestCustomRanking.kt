package model

import attributeA
import attributeB
import com.algolia.search.model.enums.CustomRanking.Asc
import com.algolia.search.model.enums.CustomRanking.Desc
import com.algolia.search.serialize.KeyAsc
import com.algolia.search.serialize.KeyDesc
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestCustomRanking {

    @Test
    fun raw() {
        "$KeyAsc($attributeA)" shouldEqual Asc(attributeA).raw
        "$KeyDesc($attributeB)" shouldEqual Desc(attributeB).raw
    }
}