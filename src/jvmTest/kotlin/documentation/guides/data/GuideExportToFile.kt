package documentation.guides.data

import documentation.index
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObjectSerializer
import kotlinx.serialization.list
import org.junit.Ignore
import org.junit.Test
import java.io.File


@Ignore
internal class GuideExportToFile {

    @Test
    fun snippet() {
        runBlocking {
            val records = index.browseObjects().flatMap { response ->
                response.hits.map { it.json }
            }
            val json = Json.stringify(JsonObjectSerializer.list, records)

            File("your_filename.json").writeText(json)
        }
    }
}