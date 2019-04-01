package documentation.parameters.performance

import com.algolia.search.dsl.numericAttributesForFiltering
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocNumericAttributesForFiltering : TestDocumentation() {

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