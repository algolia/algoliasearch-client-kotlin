package query

import client.query.helper.OptionalFilterBuilder
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class TestOptionalFilterBuilder {


    private fun builder() = OptionalFilterBuilder()

//    @Test
//    fun assign() {
//        val query = Query()
//
//        query.optionalFilterBuilder
//            .and(FilterFacet(attributeA, "valueA"))
//
//        assertEquals(listOf(listOf("attributeA:valueA")), query.optionalFilterBuilder.build())
//    }
//
//    @Test
//    fun build() {
//        builder().apply {
//            assertEquals(listOf(), this.build())
//            and(filterA, filterB)
//            or(filterC, filterD)
//            assertEquals(
//                listOf(
//                    listOf(filterA.expression),
//                    listOf(filterB.expression),
//                    listOf(filterC.expression, filterD.expression)
//                ), this.build()
//            )
//        }
//    }
}