package documentation.parameters.ranking

import com.algolia.search.dsl.customRanking
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocCustomRanking {

//    customRanking {
//        +[Asc](#parameter-option-asc)("attribute1")
//        +[Desc](#parameter-option-desc)("attribute2")
//    }

    @Test
    fun snippet1() {
        runTest {
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
