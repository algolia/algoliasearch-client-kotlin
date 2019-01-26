package data

import attributeA
import attributeB
import com.algolia.search.saas.model.CustomRanking.Asc
import com.algolia.search.saas.model.CustomRanking.Desc
import com.algolia.search.saas.serialize.KeyAsc
import com.algolia.search.saas.serialize.KeyDesc
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