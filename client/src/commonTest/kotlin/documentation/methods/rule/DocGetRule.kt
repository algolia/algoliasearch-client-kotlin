package documentation.methods.rule

import com.algolia.search.model.ObjectID
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocGetRule {

//    suspend fun Index.getRule(
//        #{objectID}: __ObjectID__,
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseRule

    @Test
    fun snippet1() {
        runTest {
            index.getRule(ObjectID("myID"))
        }
    }
}
