package documentation.parameters.advanced

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocMinProximity {

//    minProximity: Int = integer // from 1 to 7

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                minProximity = 1
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                minProximity = 2
            }

            index.search(query)
        }
    }
}