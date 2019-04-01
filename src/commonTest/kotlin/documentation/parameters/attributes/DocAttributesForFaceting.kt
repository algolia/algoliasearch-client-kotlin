package documentation.parameters.attributes

import com.algolia.search.dsl.attributesForFaceting
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocAttributesForFaceting {

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