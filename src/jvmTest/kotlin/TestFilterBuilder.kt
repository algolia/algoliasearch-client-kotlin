import client.query.Query
import client.query.helper.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestFilterBuilder {

    private val attributeA = Attribute("attributeA")
    private val filterA = FilterFacet(attributeA, "facetA")
    private val filterB = FilterFacet(attributeA, "facetB")

    private fun builder() = FilterBuilder()

    @Test
    fun assign() {
        val query = Query()

        query.filterBuilder
            .and(FilterFacet(attributeA, "valueA"))

        assertEquals("attributeA:valueA", query.filterBuilder.build())
    }

    @Test
    fun build() {
        builder().apply {
            assertEquals("", build())
            and(filterA)
            assertEquals(filterA.build(), build())
            and(filterB)
            assertEquals("${filterA.build()} AND ${filterB.build()}", build())
            or(filterA, filterB)
            assertEquals(
                "${filterA.build()} AND ${filterB.build()} AND (${filterA.build()} OR ${filterB.build()})",
                build()
            )
            clear(null)
            or(filterA, filterB)
            assertEquals("${filterA.build()} OR ${filterB.build()}", build())
        }
    }
}