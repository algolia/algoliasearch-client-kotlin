package documentation.parameters.performance

import com.algolia.search.dsl.settings
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

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
