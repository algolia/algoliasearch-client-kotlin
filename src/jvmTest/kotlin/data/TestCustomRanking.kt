package data

import attributeA
import attributeB
import client.data.CustomRanking.Asc
import client.data.CustomRanking.Desc
import client.serialize.KeyAsc
import client.serialize.KeyDesc
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