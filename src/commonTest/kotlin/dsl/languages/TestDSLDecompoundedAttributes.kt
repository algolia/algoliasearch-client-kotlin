package dsl.languages

import attributeA
import com.algolia.search.dsl.languages.DSLDecompoundedAttributes
import com.algolia.search.model.search.QueryLanguage
import com.algolia.search.model.settings.DecompoundedAttributes
import shouldEqual
import kotlin.test.Test


internal class TestDSLDecompoundedAttributes {

    @Test
    fun default() {
        val dsl = DSLDecompoundedAttributes().apply {
            german { +attributeA }
            dutch { +attributeA }
            finnish { +attributeA }
        }

        dsl.build() shouldEqual listOf(
            DecompoundedAttributes(QueryLanguage.German, attributeA),
            DecompoundedAttributes(QueryLanguage.Dutch, attributeA),
            DecompoundedAttributes(QueryLanguage.Finnish, attributeA)
        )
    }
}