package documentation.methods.rule

import com.algolia.search.model.ObjectID
import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocDeleteRule : TestDocumentation() {

//    suspend fun Index.deleteRule(
//        #{objectID}: __ObjectID__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun deleteRule() {
        runBlocking {
            index.deleteRule(ObjectID("nyID"), forwardToReplicas = true)
        }
    }
}