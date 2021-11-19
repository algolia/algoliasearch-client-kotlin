package documentation.guides.data

import documentation.index
import kotlinx.serialization.json.JsonObject
import runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideImporting {

    @Test
    fun snippet1() {
        runTest {
            val records = listOf<JsonObject>()

            index.saveObjects(records)
        }
    }
}
