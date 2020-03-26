package documentation.parameters.advanced

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocMinProximity {

//    minProximity: Int = integer // from 1 to 7

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                minProximity = 1
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val query = query("query") {
                minProximity = 2
            }

            index.search(query)
        }
    }
}
