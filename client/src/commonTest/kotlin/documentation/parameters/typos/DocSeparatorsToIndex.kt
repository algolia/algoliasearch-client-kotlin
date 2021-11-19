package documentation.parameters.typos

import com.algolia.search.dsl.settings
import documentation.index
import runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocSeparatorsToIndex {

//    separatorsToIndex: String = "separator"

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                separatorsToIndex = "+#"
            }

            index.setSettings(settings)
        }
    }
}
