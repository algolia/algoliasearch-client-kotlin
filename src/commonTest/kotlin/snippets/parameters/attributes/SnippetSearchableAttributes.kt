package snippets.parameters.attributes

import com.algolia.search.dsl.searchableAttributes
import com.algolia.search.dsl.settings
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetSearchableAttributes : TestSnippets() {

//    searchableAttributes {
//        +"attribute1"
//        +("attribute2" and "attribute3") // both attributes have the same priority
//        +("attribute4" modify [Ordered](#parameter-option-ordered))
//        +("attribute5" modify [Unordered](#parameter-option-unordered))
//    }

    @Test
    fun snippet() {
        runBlocking {
            val settings = settings {
                searchableAttributes {
                    +("title" and "alternativeTitle")
                    +"author"
                    +("text" modify Unordered)
                    +"emails.personal"
                }
            }

            index.setSettings(settings)
        }
    }
}