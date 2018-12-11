package query

import client.query.Query
import client.query.helper.Attribute
import client.query.helper.FilterFacet
import client.query.helper.OptionalFilterBuilder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestOptionalFilterBuilder {

    private val attributeA = Attribute("attributeA")
    private val attributeB = Attribute("attributeB")
    private val filterA = FilterFacet(attributeA, "facetA")
    private val filterB = FilterFacet(attributeA, "facetB")
    private val filterC = FilterFacet(attributeB, true)
    private val filterD = FilterFacet(attributeB, false)

    private fun builder() = OptionalFilterBuilder()

    @Test
    fun assign() {
        val query = Query()

        query.optionalFilterBuilder
            .and(FilterFacet(attributeA, "valueA"))

        assertEquals(listOf(listOf("attributeA:valueA")), query.optionalFilterBuilder.build())
    }

    @Test
    fun build() {
        builder().apply {
            assertEquals(listOf(), this.build())
            and(filterA, filterB)
            or(filterC, filterD)
            assertEquals(
                listOf(
                    listOf(filterA.expression),
                    listOf(filterB.expression),
                    listOf(filterC.expression, filterD.expression)
                ), this.build()
            )
        }
    }
}