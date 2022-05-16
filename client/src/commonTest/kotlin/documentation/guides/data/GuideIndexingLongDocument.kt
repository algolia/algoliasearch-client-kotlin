package documentation.guides.data

import com.algolia.search.dsl.settings
import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.Distinct
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideIndexingLongDocument {

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                attributeForDistinct = Attribute("section")
                distinct = Distinct(1)
            }

            index.setSettings(settings)
        }
    }
}
