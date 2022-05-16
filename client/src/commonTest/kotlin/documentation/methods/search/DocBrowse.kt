package documentation.methods.search

import com.algolia.search.dsl.requestOptions
import com.algolia.search.model.search.Query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocBrowse {

//    suspend fun Index.browseObjects(
//         #{query}: __Query__ = Query(),
//         #{requestOptions}: __RequestOptions?__ = null
//    ): List<ResponseSearch>

    @Test
    fun snippet1() {
        runTest {
            val query = Query()

            index.browseObjects(query).forEach { responseSearch ->
                println(responseSearch.hits)
            }
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = Query(query = "")
            val requestOptions = requestOptions {
                header("X-Algolia-User-ID", "user123")
            }

            index.browseObjects(query, requestOptions).forEach { responseSearch ->
                println(responseSearch.hits)
            }
        }
    }
}
