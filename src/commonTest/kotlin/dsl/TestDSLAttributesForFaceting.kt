package dsl

import attributeA
import attributeB
import com.algolia.search.dsl.DSLAttributesForFaceting
import com.algolia.search.model.settings.AttributeForFaceting
import shouldEqual
import kotlin.test.Test


internal class TestDSLAttributesForFaceting {

    @Test
    fun default() {
        val dsl = DSLAttributesForFaceting().apply {
            +"attributeA"
            +attributeB
        }

        dsl.build() shouldEqual listOf(
            AttributeForFaceting.Default(attributeA),
            AttributeForFaceting.Default(attributeB)
        )
    }

    @Test
    fun modifier() {
        val dsl = DSLAttributesForFaceting().apply {
            +("attributeA" modify FilterOnly)
            +(attributeB modify Searchable)
        }

        dsl.build() shouldEqual listOf(
            AttributeForFaceting.FilterOnly(attributeA),
            AttributeForFaceting.Searchable(attributeB)
        )
    }
}