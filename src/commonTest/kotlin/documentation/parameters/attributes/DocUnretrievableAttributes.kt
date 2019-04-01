package documentation.parameters.attributes

import com.algolia.search.dsl.settings
import com.algolia.search.dsl.unretrieveableAttributes
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocUnretrievableAttributes {

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