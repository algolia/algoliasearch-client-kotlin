package documentation.parameters.strategy

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.ExactOnSingleWordQuery
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocExactOnSingleWordQuery {

//    exactOnSingleWordQuery: ExactOnSingleWordQuery = [ExactOnSingleWordQuery.Attribute](#parameter-option-attribute)
//    | [ExactOnSingleWordQuery.None](#parameter-option-none)
//    | [ExactOnSingleWordQuery.Word](#parameter-option-word)

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                exactOnSingleWordQuery = ExactOnSingleWordQuery.Attribute
                // exactOnSingleWordQuery = ExactOnSingleWordQuery.None
                // exactOnSingleWordQuery = ExactOnSingleWordQuery.Word
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                exactOnSingleWordQuery = ExactOnSingleWordQuery.None
                // exactOnSingleWordQuery = ExactOnSingleWordQuery.Attribute
                // exactOnSingleWordQuery = ExactOnSingleWordQuery.Word
            }

            index.search(query)
        }
    }
}
