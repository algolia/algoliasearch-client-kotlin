package snippets.indexing

import clientAdmin1
import com.algolia.search.model.indexing.DeleteByQuery
import com.algolia.search.model.search.AroundRadius
import com.algolia.search.model.search.Point
import runBlocking
import suite.cleanIndex
import suite.testSuiteIndexName
import kotlin.test.BeforeTest
import kotlin.test.Test


internal class SnippetClearObjects {

//    suspend fun Index.clearObjects(
//        #{requestOptions}: __RequestOptions?__ = null
//    ): RevisionIndex

    private val suffix = "snippet"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)

    @BeforeTest
    fun clean() {
        runBlocking { cleanIndex(clientAdmin1, suffix) }
    }

    @Test
    fun deleteObjectBy() {
        runBlocking {
            index.clearObjects()
        }
    }
}