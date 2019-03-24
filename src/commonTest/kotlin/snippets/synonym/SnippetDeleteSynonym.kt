package snippets.synonym

import com.algolia.search.model.ObjectID
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


class SnippetDeleteSynonym : TestSnippets() {

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