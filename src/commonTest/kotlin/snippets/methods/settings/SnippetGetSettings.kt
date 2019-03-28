package snippets.methods.settings

import runBlocking
import snippets.index
import kotlin.test.Test


internal class SnippetGetSettings {

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