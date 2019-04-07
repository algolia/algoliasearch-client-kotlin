package documentation.parameters.advanced

import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocSynonyms {

//    synonyms: Boolean = true|false

    @Test
    fun snippet1() {
        runBlocking {
            val query = query("query") {
                synonyms = true
            }

            index.search(query)
        }
    }
}