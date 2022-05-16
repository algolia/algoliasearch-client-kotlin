package documentation.methods.rule

import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocClearRules {

//    suspend fun Index.clearRules(
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun snippet1() {
        runTest {
            index.clearRules(forwardToReplicas = true)
        }
    }
}
