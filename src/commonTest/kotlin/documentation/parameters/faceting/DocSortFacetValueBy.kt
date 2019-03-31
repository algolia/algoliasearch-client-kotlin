package documentation.parameters.faceting

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.SortFacetValuesBy
import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocSortFacetValueBy : TestDocumentation() {

//    sortFacetValuesBy = [SortFacetValuesBy.Alpha](#parameter-option-alpha) | [SortFacetValuesBy.Count](#parameter-option-count)

    @Test
    fun snippetSettings() {
        runBlocking {
            val settings = settings {
                sortFacetValuesBy = SortFacetValuesBy.Alpha
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippetQuery() {
        runBlocking {
            val query = query {
                sortFacetValuesBy = SortFacetValuesBy.Count

            }
            index.search(query)
        }
    }
}