package documentation.parameters.attributes

import com.algolia.search.dsl.settings
import com.algolia.search.dsl.unretrieveableAttributes
import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocUnretrievableAttributes : TestDocumentation() {

//    unretrieveableAttributes {
//        +"attribute"
//    }

    @Test
    fun snippet() {
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