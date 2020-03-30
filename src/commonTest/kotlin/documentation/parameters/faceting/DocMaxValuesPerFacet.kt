package documentation.parameters.faceting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocMaxValuesPerFacet {

//    maxValuesPerFacet: Int = maxValue

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                maxValuesPerFacet = 100
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val query = query("query") {
                maxValuesPerFacet = 50
            }

            index.search(query)
        }
    }
}
