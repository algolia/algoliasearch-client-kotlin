package documentation.parameters.advanced

import com.algolia.search.dsl.settings
import com.algolia.search.model.Attribute
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

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
