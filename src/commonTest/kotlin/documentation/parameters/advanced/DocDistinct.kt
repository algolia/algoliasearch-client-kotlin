package documentation.parameters.advanced

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import com.algolia.search.model.settings.Distinct
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocDistinct {

//    distinct: Distinct = Distinct.Value(0|1|2|3)

    @Test
    fun settings() {
        runBlocking {
            val query = settings {
                distinct = Distinct.Value(0)
                // distinct = Distinct.Value(1)
                // distinct = Distinct.Value(2)
            }

            index.setSettings(query)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                distinct = Distinct.Value(1)
                // distinct = Distinct.Value(0)
                // distinct = Distinct.Value(2)
            }

            index.search(query)
        }
    }
}