package documentation.parameters.faceting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocMaxValuesPerFacet : TestDocumentation() {

//    maxValuesPerFacet: Int = maxValue

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                maxValuesPerFacet = 100
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query {
                maxValuesPerFacet = 50
            }

            index.search(query)
        }
    }
}