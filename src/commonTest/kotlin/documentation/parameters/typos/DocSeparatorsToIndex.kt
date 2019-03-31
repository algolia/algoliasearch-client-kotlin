package documentation.parameters.typos

import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocSeparatorsToIndex : TestDocumentation() {

//    separatorsToIndex: String = "separator"

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                separatorsToIndex = "+#"
            }

            index.setSettings(settings)
        }
    }
}