package documentation.guides.security

import com.algolia.search.client.ClientSearch
import com.algolia.search.dsl.attributesForFaceting
import com.algolia.search.dsl.settings
import com.algolia.search.model.*
import com.algolia.search.model.apikey.SecuredAPIKeyRestriction
import com.algolia.search.model.indexing.Partial
import com.algolia.search.model.search.Query
import documentation.index
import kotlinx.serialization.json.jsonArray
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class GuideRestrictSubset {

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                attributesForFaceting {
                    +FilterOnly("viewable_by")
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            index.partialUpdateObject(
                ObjectID("myID1"),
                Partial.Update(
                    Attribute("viewable_bly"),
                    jsonArray {
                        +(1 as Number)
                        +(2 as Number)
                    }
                )
            )
        }
    }

    @Test
    fun snippet3() {
        runBlocking {
            val currentUserId = 1
            val restriction = SecuredAPIKeyRestriction(Query(filters = "viewable_by:$currentUserId"))


            ClientSearch.generateAPIKey(APIKey("SearchOnlyApiKeyKeptPrivate"), restriction)
        }
    }

    @Test
    fun snippet4() {
        runBlocking {
            val securedApiKey = APIKey("Secured API Key for current user") // Use the key generated earlier
            val client = ClientSearch(ApplicationID("YourApplicationID"), securedApiKey)
            val index = client.initIndex(IndexName("your_index_name"))

            index.search(Query("query"))
        }
    }
}