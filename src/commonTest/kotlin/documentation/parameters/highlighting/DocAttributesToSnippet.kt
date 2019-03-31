package documentation.parameters.highlighting

import com.algolia.search.dsl.attributesToSnippet
import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocAttributesToSnippet : TestDocumentation() {

//    attributesToSnippet {
//        +"attribute1"
//        +("attribute2" limit 10) // limits the number of words of the snippet
//    }

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                attributesToSnippet {
                    +("content" limit 80)
                    +"description"
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun settingsAll() {
        runBlocking {
            val settings = settings {
                attributesToSnippet {
                    +("*" limit 80)
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query {
                attributesToSnippet {
                    +"title"
                    +("content" limit 80)
                }
            }

            index.search(query)
        }
    }
}