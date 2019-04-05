package documentation.parameters.faceting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.SortFacetsBy
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
                sortFacetsBy = SortFacetsBy.Alpha
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                sortFacetsBy = SortFacetsBy.Count
            }

            index.search(query)
        }
    }
}