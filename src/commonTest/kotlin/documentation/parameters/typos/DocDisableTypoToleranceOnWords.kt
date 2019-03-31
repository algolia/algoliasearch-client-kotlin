package documentation.parameters.typos

import com.algolia.search.dsl.disableTypoToleranceOnWords
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocDisableTypoToleranceOnWords : TestDocumentation() {

//    disableTypoToleranceOnWords {
//        +"word"
//    }

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                disableTypoToleranceOnWords {
                    +"wheel"
                    +"1X2BCD"
                }
            }

            index.setSettings(settings)
        }
    }
}