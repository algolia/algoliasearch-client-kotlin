package documentation.reference

import com.algolia.search.dsl.customRanking
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocQuickStartCustomRanking {

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                customRanking {
                    +Desc("followers")
                }
            }

            index.setSettings(settings)
        }
    }
}
