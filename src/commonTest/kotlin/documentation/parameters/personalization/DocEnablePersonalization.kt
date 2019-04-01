package documentation.parameters.personalization

import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocEnablePersonalization {

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