package serialize

import attributeA
import attributeB
import client.data.NumericAttributeFilter
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestNumericAttribute : TestSerializer<NumericAttributeFilter>(NumericAttributeFilter) {

    private val numericAttributeA = NumericAttributeFilter(attributeA)
    private val numericAttributeB = NumericAttributeFilter(attributeB, true)

    override val item = listOf(
        numericAttributeA to JsonPrimitive(numericAttributeA.raw),
        numericAttributeB to JsonPrimitive(numericAttributeB.raw)
    )
}