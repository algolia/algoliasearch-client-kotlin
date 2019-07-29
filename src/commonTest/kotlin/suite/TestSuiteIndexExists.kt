package suite

import clientAdmin1
import kotlinx.serialization.json.json
import runBlocking
import shouldBeFalse
import shouldBeTrue
import kotlin.test.BeforeTest
import kotlin.test.Test


internal class TestSuiteIndexExists {

    private val suffix = "exists"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)

    @BeforeTest
    fun clean() {
        runBlocking {
            cleanIndex(clientAdmin1, suffix)
        }
    }

    @Test
    fun test() {
        runBlocking {
            index.apply {
                exists().shouldBeFalse()
                saveObject(json { "Key" to "Value" }).wait()
                exists().shouldBeTrue()
            }
        }
    }
}