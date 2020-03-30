package documentation.methods.indices

import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocIndexExists {

    // suspend fun Index.exists(): Boolean

    @Test
    fun snippet1() {
        runBlocking {
            index.exists()
        }
    }
}
