package suite

import clientAdmin1
import kotlin.test.Test
import kotlinx.serialization.json.json
import runBlocking
import shouldBeFalse
import shouldBeTrue

internal class TestSuiteIndexExists {

    private val suffix = "exists"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)

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
