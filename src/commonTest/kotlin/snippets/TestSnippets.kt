package snippets

import clientAdmin1
import runBlocking
import suite.cleanIndex
import suite.testSuiteIndexName
import kotlin.test.BeforeTest


abstract class TestSnippets {

    protected val suffix = "snippet"
    protected val indexName = testSuiteIndexName(suffix)
    protected val client = clientAdmin1
    protected val index = clientAdmin1.initIndex(indexName)

    @BeforeTest
    fun clean() {
        runBlocking { cleanIndex(client, suffix) }
    }
}