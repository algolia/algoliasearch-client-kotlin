package documentation.parameters.ranking

import com.algolia.search.dsl.customRanking
import com.algolia.search.dsl.settings
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocCustomRanking {

//    customRanking {
//        +[Asc](#parameter-option-asc)("attribute1")
//        +[Desc](#parameter-option-desc)("attribute2")
//    }

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                customRanking {
                    +Desc("popularity")
                    +Asc("price")
                }
            }

            index.setSettings(settings)
        }
    }
}
