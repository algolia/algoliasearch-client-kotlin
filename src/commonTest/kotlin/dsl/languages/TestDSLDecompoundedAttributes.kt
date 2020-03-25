package dsl.languages

import attributeA
import com.algolia.search.dsl.languages.DSLDecompoundedAttributes
import com.algolia.search.model.search.Language
import com.algolia.search.model.settings.DecompoundedAttributes
import shouldEqual
import kotlin.test.Test

internal class TestDSLDecompoundedAttributes {

    @Test
    fun default() {
        val dsl = DSLDecompoundedAttributes {
            german { +attributeA }
            dutch { +attributeA }
            finnish { +attributeA }
        }

        dsl shouldEqual listOf(
            DecompoundedAttributes(Language.German, attributeA),
            DecompoundedAttributes(Language.Dutch, attributeA),
            DecompoundedAttributes(Language.Finnish, attributeA)
        )
    }
}
