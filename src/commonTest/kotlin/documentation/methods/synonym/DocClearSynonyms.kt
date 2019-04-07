package documentation.methods.synonym

import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocClearSynonyms {

//    suspend fun Index.clearSynonyms(
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun snippet1() {
        runBlocking {
            index.clearSynonyms(forwardToReplicas = true)
        }
    }
}