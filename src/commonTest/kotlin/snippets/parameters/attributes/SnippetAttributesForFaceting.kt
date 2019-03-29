package snippets.parameters.attributes

import com.algolia.search.dsl.attributesForFaceting
import com.algolia.search.dsl.settings
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetAttributesForFaceting : TestSnippets() {

    @Test
    fun parameter() {
        settings {
            attributesForFaceting {
                +"attribute1"
                +("attribute2" modify FilterOnly)
                +("attribute3" modify Searchable)
            }
        }
    }

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