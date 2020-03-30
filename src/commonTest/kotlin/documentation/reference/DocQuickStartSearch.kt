package documentation.reference

import com.algolia.search.model.search.Query
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocQuickStartSearch {

    @Test
    fun snippet1() {
        runBlocking {
            // Search for a first name
            index.search(Query("jimmie"))
            // Search for a first name with typo
            index.search(Query("jimie"))
            // Search for a company
            index.search(Query("california paint"))
            // Search for a first name and a company
            index.search(Query("jimmie paint"))
        }
    }
}
