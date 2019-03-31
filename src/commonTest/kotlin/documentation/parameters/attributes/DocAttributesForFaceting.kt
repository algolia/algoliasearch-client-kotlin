package documentation.parameters.attributes

import com.algolia.search.dsl.attributesForFaceting
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocAttributesForFaceting : TestDocumentation() {

//    attributesForFaceting {
//        +"attribute1"
//        +("attribute2" modify [FilterOnly](#parameter-option-filteronly))
//        +("attribute3" modify [Searchable](#parameter-option-searchable))
//    }

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                attributesForFaceting {
                    +"author"
                    +("category" modify FilterOnly)
                    +("publisher" modify Searchable)
                }
            }

            index.setSettings(settings)
        }
    }
}