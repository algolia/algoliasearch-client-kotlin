package serialize.apikey

import com.algolia.search.model.APIKey
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer
import unknown


@RunWith(JUnit4::class)
internal class TestAPIKey : TestSerializer<APIKey>(APIKey) {

    override val items = listOf(
        APIKey(unknown) to JsonLiteral(unknown)
    )
}