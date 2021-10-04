package serialize.response

import com.algolia.search.model.response.ResponseMultiSearch
import com.algolia.search.model.response.ResultMultiSearch
import com.algolia.search.serialize.internal.JsonDebug
import loadScratch
import shouldEqual
import kotlin.test.Test

class TestResponseMultiSearch {

    @Test
    fun test() {
        val string = loadScratch("multi_queries_response.json")
        val data = JsonDebug.decodeFromString(ResponseMultiSearch.serializer(), string)
        data.results.size shouldEqual 3
        data.results[0]::class shouldEqual ResultMultiSearch.Hits::class
        data.results[1]::class shouldEqual ResultMultiSearch.Hits::class
        data.results[2]::class shouldEqual ResultMultiSearch.Facets::class
    }
}
