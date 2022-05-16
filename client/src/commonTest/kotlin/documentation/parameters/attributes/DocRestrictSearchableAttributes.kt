package documentation.parameters.attributes

import com.algolia.search.dsl.query
import com.algolia.search.dsl.restrictSearchableAttributes
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocRestrictSearchableAttributes {

//    restrictSearchableAttributes {
//        +"attribute"
//        +...
//    }

    @Test
    fun snippet1() {
        runTest {
            val query = query("query") {
                restrictSearchableAttributes {
                    +"title"
                    +"author"
                }
            }

            index.search(query)
        }
    }
}
