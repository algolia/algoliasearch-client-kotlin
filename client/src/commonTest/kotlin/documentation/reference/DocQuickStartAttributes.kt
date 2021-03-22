package documentation.reference

import com.algolia.search.dsl.searchableAttributes
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocQuickStartAttributes {

    @Test
    fun snippet1() {
        runBlocking {
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
