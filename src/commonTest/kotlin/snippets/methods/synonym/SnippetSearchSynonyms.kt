package snippets.methods.synonym

import com.algolia.search.model.synonym.SynonymQuery
import com.algolia.search.model.synonym.SynonymType
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetSearchSynonyms : TestSnippets() {

//    suspend fun Index.searchSynonyms(
//        query: __SynonymQuery__ = SynonymQuery()
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseSearchSynonyms
//
//    data class SynonymQuery(
//        var #{query}: __String?__ = null,
//        var #{page}: __Int?__ = null,
//        var #{hitsPerPage}: __Int?__ = null,
//        var [types](#method-param-type): __List<SynonymType>?__ = null,
//    )

    @Test
    fun searchSynonyms() {
        runBlocking {
            val query = SynonymQuery(
                query = "street",
                hitsPerPage = 10,
                page = 1,
                types = listOf(SynonymType.MultiWay, SynonymType.OneWay)
            )

            index.searchSynonyms(query)
        }
    }
}