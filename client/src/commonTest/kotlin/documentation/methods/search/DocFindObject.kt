package documentation.methods.search

import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.search.Query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.jsonPrimitive
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
class DocFindObject {

//    suspend fun findObject(
//        [match](#callback): __(ResponseSearch.Hit) -> Boolean__,
//        #{query}: __Query__ = Query(),
//        #{paginate}: __Boolean__ = true,
//        #{requestOptions}: __RequestOptions?__ = null
//    ): ResponseHitWithPosition?

    @Test
    fun snippet1() {
        runTest {
            val match: (ResponseSearch.Hit) -> Boolean = {
                it.json.getValue("firstname").jsonPrimitive.content == "Jimmie"
            }
            index.findObject(match)
            index.findObject(match, Query("query string"))
            index.findObject(match, Query("query string"), paginate = false)
        }
    }
}
