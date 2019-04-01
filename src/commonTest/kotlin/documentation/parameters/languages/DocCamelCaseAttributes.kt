package documentation.parameters.languages

import com.algolia.search.dsl.camelCaseAttributes
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocCamelCaseAttributes : TestDocumentation() {

//    camelCaseAttributes { +"attribute" }

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                camelCaseAttributes { +"description" }
            }

            index.setSettings(settings)
        }
    }
}