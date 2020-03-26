package documentation.parameters.performance

import com.algolia.search.dsl.numericAttributesForFiltering
import com.algolia.search.dsl.settings
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocNumericAttributesForFiltering {

//    numericAttributesForFiltering {
//        +"attribute1"
//        +"attribute2" [equalOnly](#parameter-option-equalonly) true|false
//    }

    @Test
    fun snippet1() {
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
