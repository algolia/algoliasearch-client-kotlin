package dsl.attributes

import attributeA
import attributeB
import com.algolia.search.dsl.all
import com.algolia.search.dsl.attributes.DSLAttributesToRetrieve
import com.algolia.search.model.Attribute
import shouldEqual
import kotlin.test.Test


internal class TestDSLAttributesToRetrieve {

    @Test
    fun default() {
        val dsl = DSLAttributesToRetrieve().apply {
            +"attributeA"
            +attributeB
        }

        dsl.build() shouldEqual listOf(attributeA, attributeB)
    }

    @Test
    fun exclude() {
        val dsl = DSLAttributesToRetrieve().apply {
            +attributeA
            +attributeB
            excludeAttributes = true
        }

        dsl.build() shouldEqual listOf(
            Attribute("-attributeA"),
            Attribute("-attributeB"),
            all
        )
    }
}