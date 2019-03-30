package snippets.parameters.attributes

import com.algolia.search.dsl.attributesForFaceting
import com.algolia.search.dsl.settings
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetAttributesForFaceting : TestSnippets() {

//    attributesForFaceting {
//        +"attribute1"
//        +("attribute2" modify [FilterOnly](#parameter-option-filteronly))
//        +("attribute3" modify [Searchable](#parameter-option-searchable))
//    }

    @Test
    fun snippet() {
        runBlocking {
            val settings = settings {
                attributesForFaceting {
                    +"author"
                    +("category" modify FilterOnly)
                    +("publisher" modify Searchable)
                }
            }

            index.setSettings(settings)
        }
    }
}