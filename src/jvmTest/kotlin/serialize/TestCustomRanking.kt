package serialize

import attributeA
import attributeB
import client.data.CustomRanking
import client.data.CustomRanking.*
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import tmp.TestSerializer
import unknown


@RunWith(JUnit4::class)
internal class TestCustomRanking : TestSerializer<CustomRanking>(CustomRanking) {

    private val asc = Asc(attributeA)
    private val desc = Desc(attributeB)

    override val item = listOf(
        asc to JsonPrimitive(asc.raw),
        desc to JsonPrimitive(desc.raw),
        Unknown(unknown) to JsonPrimitive(unknown)
    )

    override val items = listOf(
        listOf(
            asc,
            desc,
            Unknown(unknown)
        ) to jsonArray {
            +asc.raw
            +desc.raw
            +unknown
        }
    )
}