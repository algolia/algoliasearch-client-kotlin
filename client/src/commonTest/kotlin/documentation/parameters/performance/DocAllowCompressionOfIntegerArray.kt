package documentation.parameters.performance

import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocAllowCompressionOfIntegerArray {

//    allowCompressionOfIntegerArray: Boolean = true|false

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                allowCompressionOfIntegerArray = true
            }

            index.setSettings(settings)
        }
    }
}
