package documentation.parameters.faceting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.SortFacetValuesBy
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocSortFacetValueBy {

//    sortFacetValuesBy: SortFacetValuesBy = [SortFacetValuesBy.Alpha](#parameter-option-alpha)
//    | [SortFacetValuesBy.Count](#parameter-option-count)

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                sortFacetValuesBy = SortFacetValuesBy.Alpha
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                sortFacetValuesBy = SortFacetValuesBy.Count

            }
            index.search(query)
        }
    }
}