package documentation.parameters.advanced

import com.algolia.search.dsl.settings
import com.algolia.search.model.Attribute
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocAttributeForDistinct {

//  attributeForDistinct: Attribute = Attribute("attribute")

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                attributeForDistinct = Attribute("url")
            }

            index.setSettings(settings)
        }
    }
}
