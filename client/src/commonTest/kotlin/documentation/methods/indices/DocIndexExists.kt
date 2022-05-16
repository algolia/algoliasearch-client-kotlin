package documentation.methods.indices

import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocIndexExists {

    // suspend fun Index.exists(): Boolean

    @Test
    fun snippet1() {
        runTest {
            index.exists()
        }
    }
}
