package documentation.methods.synonym

import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

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
