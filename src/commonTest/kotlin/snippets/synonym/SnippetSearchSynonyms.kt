package snippets.synonym

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
//        #{query}: __String?__ = null,
//        #{page}: __Int?__ = null,
//        #{hitsPerPage}: __Int?__ = null,
//        [types](#method-param-type): __List<SynonymType>?__ = null,
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