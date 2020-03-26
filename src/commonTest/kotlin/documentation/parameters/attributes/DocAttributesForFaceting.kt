package documentation.parameters.attributes

import com.algolia.search.dsl.attributesForFaceting
import com.algolia.search.dsl.settings
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocAttributesForFaceting {

//    attributesForFaceting {
//        +"attribute1"
//        +[FilterOnly](#parameter-option-filteronly)"attribute2")
//        +[Searchable](#parameter-option-searchable)("attribute3")
//    }

    @Test
    fun snippet1() {
        runBlocking {
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
