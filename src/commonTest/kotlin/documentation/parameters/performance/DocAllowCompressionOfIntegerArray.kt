package documentation.parameters.performance

import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocAllowCompressionOfIntegerArray : TestDocumentation() {

//    allowCompressionOfIntegerArray: Boolean = true|false

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                allowCompressionOfIntegerArray = true
            }

            index.setSettings(settings)
        }
    }
}