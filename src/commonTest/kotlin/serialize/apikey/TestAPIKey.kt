package serialize.apikey

import com.algolia.search.model.APIKey
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown


internal class TestAPIKey : TestSerializer<APIKey>(APIKey) {

    override val items = listOf(
        APIKey(unknown) to JsonLiteral(unknown)
    )
}