package documentation.parameters.advanced

import com.algolia.search.dsl.settings
import com.algolia.search.model.Attribute
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocAttributeForDistinct {

//  attributeForDistinct: Attribute = Attribute("attribute")

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                attributeForDistinct = Attribute("url")
            }

            index.setSettings(settings)
        }
    }
}
