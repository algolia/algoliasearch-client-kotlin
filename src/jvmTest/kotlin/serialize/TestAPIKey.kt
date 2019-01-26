package serialize

import com.algolia.search.saas.model.APIKey
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestAPIKey : TestSerializer<APIKey>(APIKey) {

    override val items = listOf(
        APIKey(unknown) to JsonLiteral(unknown)
    )
}