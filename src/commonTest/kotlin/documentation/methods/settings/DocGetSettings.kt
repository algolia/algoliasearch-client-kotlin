package documentation.methods.settings

import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocGetSettings {

//    suspend fun Index.getSettings(
//        requestOptions: __RequestOptions?__ = null
//    ): Settings

    @Test
    fun getSettings() {
        runBlocking {
            val settings = index.getSettings()

            println(settings)
        }
    }
}