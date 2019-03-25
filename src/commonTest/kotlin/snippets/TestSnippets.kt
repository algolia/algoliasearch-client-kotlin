package snippets

import clientAdmin1
import runBlocking
import suite.cleanIndex
import suite.testSuiteIndexName
import kotlin.test.AfterTest


internal abstract class TestSnippets {

    protected val suffix = "snippet"
    protected val indexName = testSuiteIndexName(suffix)
    protected val indexName1 = indexName
    protected val indexName2 = indexName.copy(indexName.raw + "_copy")
    protected val client = clientAdmin1
    protected val index = clientAdmin1.initIndex(indexName)

    @AfterTest
    fun before() {
        runBlocking {
            cleanIndex(client, suffix, true)
        }
    }
}