package documentation.methods.synonym

import documentation.index
import kotlinx.coroutines.test.runTest
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
        runTest {
            index.clearSynonyms(forwardToReplicas = true)
        }
    }
}
