package documentation.parameters.languages

import com.algolia.search.dsl.camelCaseAttributes
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocCamelCaseAttributes {

//    camelCaseAttributes {
//        +"attribute"
//        +...
//    }

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                camelCaseAttributes { +"description" }
            }

            index.setSettings(settings)
        }
    }
}
