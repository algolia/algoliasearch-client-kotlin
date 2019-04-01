package documentation.parameters.strategy

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.ExactOnSingleWordQuery
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocExactOnSingleWordQuery {

//    exactOnSingleWordQuery: ExactOnSingleWordQuery = [ExactOnSingleWordQuery.Attribute](#parameter-option-attribute)
//    | [ExactOnSingleWordQuery.None](#parameter-option-none)
//    | [ExactOnSingleWordQuery.Word](#parameter-option-word)

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                exactOnSingleWordQuery = ExactOnSingleWordQuery.Attribute
                // exactOnSingleWordQuery = ExactOnSingleWordQuery.None
                // exactOnSingleWordQuery = ExactOnSingleWordQuery.Word
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                exactOnSingleWordQuery = ExactOnSingleWordQuery.None
                // exactOnSingleWordQuery = ExactOnSingleWordQuery.Attribute
                // exactOnSingleWordQuery = ExactOnSingleWordQuery.Word
            }

            index.search(query)
        }
    }
}