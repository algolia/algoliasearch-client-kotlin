package documentation.marketing

import com.algolia.search.model.IndexName
import documentation.client
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class MarketingSnippet {

    @Test
    fun snippet1() {
        runTest {
            val index = client.initIndex(IndexName("contacts"))
            val json = buildJsonObject {
                put("firstname", "Jimmie")
                put("lastname", "Barninger")
                put("company", "California Paint")
            }

            index.saveObject(json)
        }
    }
}
