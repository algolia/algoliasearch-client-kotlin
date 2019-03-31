package documentation.methods.settings

import runBlocking
import documentation.index
import kotlin.test.Test


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