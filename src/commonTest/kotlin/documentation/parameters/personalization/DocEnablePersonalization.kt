package documentation.parameters.personalization

import com.algolia.search.dsl.query
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocEnablePersonalization : TestDocumentation() {

//    enablePersonalization: Boolean = true|false

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                enablePersonalization = true
            }

            index.search(query)
        }
    }
}