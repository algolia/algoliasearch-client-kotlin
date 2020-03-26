package documentation.parameters.typos

import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocSeparatorsToIndex {

//    separatorsToIndex: String = "separator"

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                separatorsToIndex = "+#"
            }

            index.setSettings(settings)
        }
    }
}