package serialize

import attributeA
import attributeB
import com.algolia.search.saas.data.CustomRanking
import com.algolia.search.saas.data.CustomRanking.*
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
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
}