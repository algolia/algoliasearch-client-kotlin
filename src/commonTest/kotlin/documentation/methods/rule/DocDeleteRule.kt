package documentation.methods.rule

import com.algolia.search.model.ObjectID
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocDeleteRule {

//    suspend fun Index.deleteRule(
//        #{objectID}: __ObjectID__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun snippet1() {
        runBlocking {
            index.deleteRule(ObjectID("nyID"), forwardToReplicas = true)
        }
    }
}
