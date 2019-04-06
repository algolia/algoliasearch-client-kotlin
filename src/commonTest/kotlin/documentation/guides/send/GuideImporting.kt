package documentation.guides.send

import documentation.index
import kotlinx.serialization.json.JsonObject
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class GuideImporting {

    @Test
    fun snippet() {
        runBlocking {
            val records = listOf<JsonObject>()

            index.saveObjects(records)
        }
    }
}