package dsl.attributes

import attributeA
import attributeB
import com.algolia.search.dsl.attributes.DSLAttributesForFaceting
import com.algolia.search.model.settings.AttributeForFaceting
import shouldEqual
import kotlin.test.Test

internal class TestDSLAttributesForFaceting {

    @Test
    fun default() {
        val dsl = DSLAttributesForFaceting {
            +"attributeA"
            +attributeB
        }

        dsl shouldEqual listOf(
            AttributeForFaceting.Default(attributeA),
            AttributeForFaceting.Default(attributeB)
        )
    }

    @Test
    fun modifier() {
        val dsl = DSLAttributesForFaceting {
            +FilterOnly("attributeA")
            +Searchable(attributeB)
        }

        dsl shouldEqual listOf(
            AttributeForFaceting.FilterOnly(attributeA),
            AttributeForFaceting.Searchable(attributeB)
        )
    }
}
