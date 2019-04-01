package documentation.parameters.attributes

import com.algolia.search.dsl.settings
import com.algolia.search.dsl.unretrieveableAttributes
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocUnretrievableAttributes : TestDocumentation() {

//    unretrieveableAttributes {
//        +"attribute"
//        +...
//    }

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                unretrieveableAttributes {
                    +"total_number_of_sales"
                }
            }

            index.setSettings(settings)
        }
    }
}