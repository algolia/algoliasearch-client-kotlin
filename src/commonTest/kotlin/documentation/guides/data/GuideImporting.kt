package documentation.guides.data

import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import kotlinx.serialization.json.JsonObject
import runBlocking

@Ignore
internal class GuideImporting {

    @Test
    fun snippet1() {
        runBlocking {
            val records = listOf<JsonObject>()

            index.saveObjects(records)
        }
    }
}
