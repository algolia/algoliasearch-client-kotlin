package documentation.parameters.languages

import com.algolia.search.dsl.decompoundedAttributes
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocDecompoundedAttributes : TestDocumentation() {

//    decompoundedAttributes {
//        +language { +"attribute" }
//        +...
//    }

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                decompoundedAttributes {
                    +german { +"name" }
                }
            }

            index.setSettings(settings)
        }
    }
}