package documentation.parameters.faceting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocMaxValuesPerFacet {

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
            val query = query("query") {
                maxValuesPerFacet = 50
            }

            index.search(query)
        }
    }
}