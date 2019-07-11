package documentation.methods.indices

import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocIndexExists  {

    // suspend fun Index.exists(): Boolean

    @Test
    fun snippet1() {
        runBlocking {
            index.exists()
        }
    }
}