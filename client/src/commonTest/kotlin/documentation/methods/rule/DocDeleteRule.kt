package documentation.methods.rule

import com.algolia.search.model.ObjectID
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocDeleteRule {

//    suspend fun Index.deleteRule(
//        #{objectID}: __ObjectID__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun snippet1() {
        runTest {
            index.deleteRule(ObjectID("nyID"), forwardToReplicas = true)
        }
    }
}
