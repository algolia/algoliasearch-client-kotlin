package documentation.parameters.ranking

import com.algolia.search.dsl.customRanking
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocCustomRanking : TestDocumentation() {

//    customRanking {
//        +("attribute1" modify [Asc](#parameter-option-asc))
//        +("attribute2" modify [Desc](#parameter-option-desc))
//    }

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                customRanking {
                    +("popularity" modify Desc)
                    +("price" modify Asc)
                }
            }

            index.setSettings(settings)
        }
    }
}