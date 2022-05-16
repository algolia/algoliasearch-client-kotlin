package documentation.guides.data

import com.algolia.search.dsl.searchableAttributes
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER")
@Ignore
internal class GuideSearchableAttributes {

    @Test
    fun snippet1() {
        runTest {
            // "title" and "comments" have the same priority
            var settings = settings {
                searchableAttributes {
                    +"title,comments"
                    +"ingredients"
                }
            }

            // "title" has the highest priority, then "ingredients", then "comments"
            settings = settings {
                searchableAttributes {
                    +"title"
                    +"ingredients"
                    +"comments"
                }
            }

            index.setSettings(settings)
        }
    }
}
