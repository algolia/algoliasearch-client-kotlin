package dsl.attributes

import attributeA
import attributeB
import com.algolia.search.dsl.attributes.DSLSearchableAttributes
import com.algolia.search.model.settings.SearchableAttribute
import shouldEqual
import kotlin.test.Test


internal class TestDSLSearchableAttributes {

    @Test
    fun default() {
        val dsl = DSLSearchableAttributes().apply {
            +"attributeA"
            +attributeB
        }

        dsl.build() shouldEqual listOf(
            SearchableAttribute.Default(attributeA),
            SearchableAttribute.Default(attributeB)
        )
    }

    @Test
    fun multiple() {
        val dsl = DSLSearchableAttributes().apply {
            +("attributeA" and "attributeB")
            +(attributeA and attributeB)
        }

        dsl.build() shouldEqual listOf(
            SearchableAttribute.Default(attributeA, attributeB),
            SearchableAttribute.Default(attributeA, attributeB)
        )
    }

    @Test
    fun modifier() {
        val dsl = DSLSearchableAttributes().apply {
            +("attributeA" modify Ordered)
            +(attributeB modify Unordered)
        }

        dsl.build() shouldEqual listOf(
            SearchableAttribute.Ordered(attributeA),
            SearchableAttribute.Unordered(attributeB)
        )
    }
}