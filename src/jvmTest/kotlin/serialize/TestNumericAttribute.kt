package serialize

import attributeA
import attributeB
import client.data.NumericAttributeFilter
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import tmp.TestSerializer


@RunWith(JUnit4::class)
internal class TestNumericAttribute : TestSerializer<NumericAttributeFilter>(
    NumericAttributeFilter,
    NumericAttributeFilter
) {

    private val numericAttributeA = NumericAttributeFilter(attributeA)
    private val numericAttributeB = NumericAttributeFilter(attributeB, true)

    override val item = listOf(
        numericAttributeA to JsonPrimitive(numericAttributeA.raw),
        numericAttributeB to JsonPrimitive(numericAttributeB.raw)
    )
    override val items = listOf(
        listOf(
            numericAttributeA,
            numericAttributeB
        ) to jsonArray {
            +numericAttributeA.raw
            +numericAttributeB.raw
        }
    )
}