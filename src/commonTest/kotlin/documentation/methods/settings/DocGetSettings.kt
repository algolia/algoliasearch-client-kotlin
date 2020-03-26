package documentation.methods.settings

import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocGetSettings {

//    suspend fun Index.getSettings(
//        requestOptions: __RequestOptions?__ = null
//    ): Settings

    @Test
    fun snippet1() {
        runBlocking {
            val settings = index.getSettings()

            println(settings)
        }
    }
}
