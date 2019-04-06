package documentation.guides.results.grouping

import com.algolia.search.dsl.query
import com.algolia.search.dsl.searchableAttributes
import com.algolia.search.dsl.settings
import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.Distinct
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class GuideItemVariations {

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                searchableAttributes {
                    +"model"
                    +"type"
                    +"color"
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val settings = settings {
                attributeForDistinct = Attribute("model")
                distinct = Distinct(1)
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet3() {
        runBlocking {
            val query = query("query") {
                distinct = Distinct(1)
            }

            index.search(query)
        }
    }
}