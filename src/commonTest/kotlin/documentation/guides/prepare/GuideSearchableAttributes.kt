package documentation.guides.prepare

import com.algolia.search.dsl.searchableAttributes
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER")
@Ignore
internal class GuideSearchableAttributes {

    @Test
    fun snippet() {
        runBlocking {
            // "title" and "comments" have the same priority
            var settings = settings {
                searchableAttributes {
                    +("title" and "comments")
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