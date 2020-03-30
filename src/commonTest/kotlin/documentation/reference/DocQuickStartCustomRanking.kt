package documentation.reference

import com.algolia.search.dsl.customRanking
import com.algolia.search.dsl.settings
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocQuickStartCustomRanking {

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                customRanking {
                    +Desc("followers")
                }
            }

            index.setSettings(settings)
        }
    }
}
