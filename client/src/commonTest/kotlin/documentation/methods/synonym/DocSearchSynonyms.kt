package documentation.methods.synonym

import com.algolia.search.model.synonym.SynonymQuery
import com.algolia.search.model.synonym.SynonymType
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocSearchSynonyms {

//    suspend fun Index.searchSynonyms(
//        query: __SynonymQuery__ = SynonymQuery()
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseSearchSynonyms
//
//    data class SynonymQuery(
//        var #{query}: __String?__ = null,
//        var #{page}: __Int?__ = null,
//        var #{hitsPerPage}: __Int?__ = null,
//        var [synonymTypes](#method-param-type): __List<SynonymType>?__ = null,
//    )

    @Test
    fun snippet1() {
        runTest {
            val query = SynonymQuery(
                query = "street",
                hitsPerPage = 10,
                page = 1,
                synonymTypes = listOf(SynonymType.MultiWay, SynonymType.OneWay)
            )

            index.searchSynonyms(query)
        }
    }
}
