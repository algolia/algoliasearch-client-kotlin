package serialize.settings

import attributeA
import attributeB
import com.algolia.search.model.settings.NumericAttributeFilter
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer


@RunWith(JUnit4::class)
internal class TestNumericAttribute : TestSerializer<NumericAttributeFilter>(NumericAttributeFilter) {

    override val items = listOf(
        NumericAttributeFilter(attributeA) to JsonLiteral(attributeA.raw),
        item to json
    )

    companion object {

        val item = NumericAttributeFilter(attributeB, true)
        val json = JsonLiteral(item.raw)
    }
}