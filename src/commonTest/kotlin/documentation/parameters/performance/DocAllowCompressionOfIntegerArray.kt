package documentation.parameters.performance

import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocAllowCompressionOfIntegerArray {

//    allowCompressionOfIntegerArray: Boolean = true|false

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                allowCompressionOfIntegerArray = true
            }

            index.setSettings(settings)
        }
    }
}