package documentation.parameters.advanced

import com.algolia.search.dsl.settings
import com.algolia.search.model.Attribute
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocAttributeForDistinct : TestDocumentation() {

//  attributeForDistinct: Attribute = Attribute("attribute")

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                attributeForDistinct = Attribute("url")
            }

            index.setSettings(settings)
        }
    }
}