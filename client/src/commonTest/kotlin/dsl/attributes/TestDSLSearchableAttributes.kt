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
        val dsl = DSLSearchableAttributes {
            +"attributeA"
            +attributeB
        }

        dsl shouldEqual listOf(
            SearchableAttribute.Default(attributeA),
            SearchableAttribute.Default(attributeB)
        )
    }

    @Test
    fun modifier() {
        val dsl = DSLSearchableAttributes {
            +"attributeA"
            +Unordered(attributeB)
        }

        dsl shouldEqual listOf(
            SearchableAttribute.Default(attributeA),
            SearchableAttribute.Unordered(attributeB)
        )
    }
}
