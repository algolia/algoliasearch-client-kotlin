package serialize

import attributeA
import attributeB
import client.data.Ranking
import client.data.Ranking.*
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import tmp.TestSerializer
import unknown


@RunWith(JUnit4::class)
internal class TestRanking : TestSerializer<Ranking>(Ranking, Ranking) {

    private val asc = Asc(attributeA)
    private val desc = Desc(attributeB)

    override val item = listOf(
        Geo to JsonPrimitive(Geo.raw),
        Typo to JsonPrimitive(Typo.raw),
        Words to JsonPrimitive(Words.raw),
        Filters to JsonPrimitive(Filters.raw),
        Proximity to JsonPrimitive(Proximity.raw),
        Attribute to JsonPrimitive(Attribute.raw),
        Exact to JsonPrimitive(Exact.raw),
        Custom to JsonPrimitive(Custom.raw),
        asc to JsonPrimitive(asc.raw),
        desc to JsonPrimitive(desc.raw),
        Unknown(unknown) to JsonPrimitive(unknown)
    )
    override val items = listOf(
        listOf(
            Geo,
            Typo,
            Words,
            Filters,
            Proximity,
            Attribute,
            Exact,
            Custom,
            asc,
            desc,
            Unknown(unknown)
        ) to jsonArray {
            +Geo.raw
            +Typo.raw
            +Words.raw
            +Filters.raw
            +Proximity.raw
            +Attribute.raw
            +Exact.raw
            +Custom.raw
            +asc.raw
            +desc.raw
            +unknown
        }
    )
}