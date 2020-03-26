package documentation.marketing

import com.algolia.search.model.IndexName
import documentation.client
import kotlinx.serialization.json.json
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class MarketingSnippet {

    @Test
    fun snippet1() {
        runBlocking {
            val index = client.initIndex(IndexName("contacts"))
            val json = json {
                "firstname" to "Jimmie"
                "lastname" to "Barninger"
                "company" to "California Paint"
            }

            index.saveObject(json)
        }
    }
}