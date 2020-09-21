package suite

import clientAdmin1
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import runBlocking
import shouldBeFalse
import shouldBeTrue
import kotlin.test.Test

internal class TestSuiteIndexExists {

    private val suffix = "exists"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)

    @Test
    fun test() {
        runBlocking {
            index.apply {
                exists().shouldBeFalse()
                saveObject(buildJsonObject { put("Key", "Value") }).wait()
                exists().shouldBeTrue()
            }
        }
    }
}
