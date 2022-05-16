package documentation.parameters.attributes

import com.algolia.search.dsl.attributesForFaceting
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocAttributesForFaceting {

//    attributesForFaceting {
//        +"attribute1"
//        +[FilterOnly](#parameter-option-filteronly)"attribute2")
//        +[Searchable](#parameter-option-searchable)("attribute3")
//    }

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                attributesForFaceting {
                    +"author"
                    +FilterOnly("category")
                    +Searchable("publisher")
                }
            }

            index.setSettings(settings)
        }
    }
}
