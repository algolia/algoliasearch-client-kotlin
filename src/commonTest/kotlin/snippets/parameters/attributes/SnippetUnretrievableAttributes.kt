package snippets.parameters.attributes

import com.algolia.search.dsl.settings
import com.algolia.search.dsl.unretrieveableAttributes
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetUnretrievableAttributes : TestSnippets() {

    @Test
    fun parameter() {
        settings {
            unretrieveableAttributes {
                +"attribute"
            }
        }
    }

    @Test
    fun snippet() {
        runBlocking {
            val settings = settings {
                unretrieveableAttributes {
                    +"total_number_of_sales"
                }
            }

            index.setSettings(settings)
        }
    }
}