package documentation.parameters.attributes

import com.algolia.search.dsl.searchableAttributes
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocSearchableAttributes {

//    searchableAttributes {
//        +"attribute1"
//        +("attribute2" and "attribute3") // both attributes have the same priority
//        +("attribute4" modify [Ordered](#parameter-option-ordered))
//        +("attribute5" modify [Unordered](#parameter-option-unordered))
//    }

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                searchableAttributes {
                    +"title,alternativeTitle"
                    +"author"
                    +("text" modify Unordered)
                    +"emails.personal"
                }
            }

            index.setSettings(settings)
        }
    }
}