package documentation.methods.search

import com.algolia.search.model.ObjectID
import com.algolia.search.model.response.ResponseSearch
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
@Suppress("UNUSED_VARIABLE")
class DocGetObjectPosition {

    // fun ResponseSearch.getObjectPosition(#{objectID}: __ObjectID__): Int

    private val responseSearch = ResponseSearch()

    @Test
    fun snippet1() {
        runBlocking {
            val position: Int = responseSearch.getObjectPosition(ObjectID("a-unique-identifier"))
        }
    }
}
