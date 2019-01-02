package serialize

import attributeA
import attributeB
import com.algolia.search.saas.data.NumericAttributeFilter
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestNumericAttribute : TestSerializer<NumericAttributeFilter>(NumericAttributeFilter) {

    override val items = listOf(
        NumericAttributeFilter(attributeA),
        NumericAttributeFilter(attributeB, true)
    )
}