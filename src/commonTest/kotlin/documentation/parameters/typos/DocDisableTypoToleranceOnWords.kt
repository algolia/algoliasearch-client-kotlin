package documentation.parameters.typos

import com.algolia.search.dsl.disableTypoToleranceOnWords
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocDisableTypoToleranceOnWords {

//    disableTypoToleranceOnWords {
//        +"word"
//        +...
//    }

    @Test
    fun snippet1() {
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
