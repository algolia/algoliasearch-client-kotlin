package documentation.guides.data

import com.algolia.search.dsl.settings
import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.Distinct
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class GuideIndexingLongDocument {

    @Test
    fun snippet() {
        runBlocking {
            val settings = settings {
                attributeForDistinct = Attribute("section")
                distinct = Distinct(1)
            }

            index.setSettings(settings)
        }
    }
}