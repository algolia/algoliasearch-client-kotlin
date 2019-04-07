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
//        +"attribute2, attribute3" // both attributes have the same priority
//        +[Ordered](#parameter-option-ordered)("attribute4")
//        +[Unordered](#parameter-option-unordered)("attribute5")
//    }

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                searchableAttributes {
                    +"title,alternativeTitle"
                    +"author"
                    +Unordered("text")
                    +"emails.personal"
                }
            }

            index.setSettings(settings)
        }
    }
}