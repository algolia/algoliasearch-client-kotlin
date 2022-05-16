package documentation.guides.security

import com.algolia.search.client.ClientSearch
import com.algolia.search.dsl.attributesForFaceting
import com.algolia.search.dsl.settings
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.apikey.SecuredAPIKeyRestriction
import com.algolia.search.model.indexing.Partial
import com.algolia.search.model.search.Query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideRestrictSubset {

    @Test
    fun snippet1() {
        runTest {
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
        runTest {
            index.partialUpdateObject(
                ObjectID("myID1"),
                Partial.Update(
                    Attribute("viewable_bly"),
                    buildJsonArray {
                        add((1 as Number))
                        add((2 as Number))
                    }
                )
            )
        }
    }

    @Test
    fun snippet3() {
        runTest {
            val currentUserId = 1
            val restriction = SecuredAPIKeyRestriction(Query(filters = "viewable_by:$currentUserId"))

            ClientSearch.generateAPIKey(APIKey("SearchOnlyApiKeyKeptPrivate"), restriction)
        }
    }

    @Test
    fun snippet4() {
        runTest {
            val securedApiKey = APIKey("Secured API Key for current user") // Use the key generated earlier
            val client = ClientSearch(ApplicationID("YourApplicationID"), securedApiKey)
            val index = client.initIndex(IndexName("your_index_name"))

            index.search(Query("query"))
        }
    }
}
