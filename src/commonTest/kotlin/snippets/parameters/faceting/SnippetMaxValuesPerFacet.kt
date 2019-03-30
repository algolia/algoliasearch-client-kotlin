package snippets.parameters.faceting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetMaxValuesPerFacet : TestSnippets() {

//    maxValuesPerFacet: Int = maxValue

    @Test
    fun snippetSettings() {
        runBlocking {
            val settings = settings {
                maxValuesPerFacet = 100
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippetQuery() {
        runBlocking {
            val query = query {
                maxValuesPerFacet = 50
            }

            index.search(query)
        }
    }
}