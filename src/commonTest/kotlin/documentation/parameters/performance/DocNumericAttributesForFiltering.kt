package documentation.parameters.performance

import com.algolia.search.dsl.numericAttributesForFiltering
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocNumericAttributesForFiltering {

//    numericAttributesForFiltering {
//        +"attribute1"
//        +"attribute2" [equalOnly](#parameter-option-equalonly) true|false
//    }

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                numericAttributesForFiltering {
                    +"quantity"
                    +"popularity"
                }
            }

            index.setSettings(settings)
        }
    }
}