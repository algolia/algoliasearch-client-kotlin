package documentation.reference

import com.algolia.search.dsl.searchableAttributes
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocQuickStartAttributes {

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                searchableAttributes {
                    +"lastname"
                    +"firstname"
                    +"company"
                }
            }

            index.setSettings(settings)
        }
    }
}
