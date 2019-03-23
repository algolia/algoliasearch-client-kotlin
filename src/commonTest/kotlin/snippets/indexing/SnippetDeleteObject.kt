package snippets.indexing

import suite.cleanIndex
import suite.testSuiteIndexName
import kotlin.test.AfterTest


internal class SnippetDeleteObject {

    private val suffix = "snippet"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)

    @AfterTest
    fun clean() {
        runBlocking { cleanIndex(clientAdmin1, suffix) }
    }

}