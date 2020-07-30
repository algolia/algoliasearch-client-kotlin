package serialize.apikey

import com.algolia.search.model.APIKey
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestAPIKey : TestSerializer<APIKey>(APIKey) {

    override val items = listOf(
        APIKey(unknown) to JsonPrimitive(unknown)
    )
}
