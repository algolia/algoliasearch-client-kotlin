package documentation.methods.search

import com.algolia.search.model.APIKey
import com.algolia.search.model.apikey.getSecuredApiKeyRemainingValidity
import kotlin.test.Ignore
import kotlin.test.Test

@Suppress("UNUSED_VARIABLE")
@Ignore
class DocSecuredAPIKey {

    // fun [APIKey](#apiKey).getSecuredApiKeyRemainingValidity(): Long

    @Test
    fun snippet1() {
        val remainingValidity = APIKey("YourSecuredAPIkey").getSecuredApiKeyRemainingValidity()
    }
}
