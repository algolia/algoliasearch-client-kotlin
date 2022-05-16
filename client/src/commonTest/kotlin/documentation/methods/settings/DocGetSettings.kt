package documentation.methods.settings

import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocGetSettings {

//    suspend fun Index.getSettings(
//        requestOptions: __RequestOptions?__ = null
//    ): Settings

    @Test
    fun snippet1() {
        runTest {
            val settings = index.getSettings()

            println(settings)
        }
    }
}
