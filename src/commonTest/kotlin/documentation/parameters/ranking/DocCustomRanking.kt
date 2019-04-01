package documentation.parameters.ranking

import com.algolia.search.dsl.customRanking
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocCustomRanking {

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