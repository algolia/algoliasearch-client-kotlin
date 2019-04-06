package documentation.methods.search

import com.algolia.search.dsl.requestOptions
import com.algolia.search.model.search.Query
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocBrowse {

//    suspend fun Index.browseObjects(
//         #{query}: __Query__ = Query(),
//         #{requestOptions}: __RequestOptions?__ = null,
//         block: __suspend (ResponseSearch) -> Unit)__
//    )

    @Test
    fun example() {
        runBlocking {
            val query = Query()

            index.browseObjects(query) { responseSearch ->
                println(responseSearch.hits)
            }
        }
    }

    @Test
    fun extraHttpHeaders() {
        runBlocking {
            val query = Query(query = "")
            val requestOptions = requestOptions {
                header("X-Algolia-User-ID", "user123")
            }

            index.browseObjects(query, requestOptions) { responseSearch ->
                println(responseSearch.hits)
            }
        }
    }
}