package documentation.methods.synonym

import com.algolia.search.model.ObjectID
import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocDeleteSynonym : TestDocumentation() {

//    suspend fun Index.deleteSynonym(
//        #{objectID}: __ObjectID__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: RequestOptions? = null
//    ): DeletionIndex

    @Test
    fun deleteSynonym() {
        runBlocking {
            index.deleteSynonym(ObjectID("myID"), forwardToReplicas = true)
        }
    }
}