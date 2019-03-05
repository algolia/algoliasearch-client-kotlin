package serialize.settings

import attributeA
import attributeB
import com.algolia.search.model.settings.DecompoundedAttributes
import com.algolia.search.model.search.QueryLanguage
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer


@RunWith(JUnit4::class)
internal class TestDecompoundedAttributes : TestSerializer<DecompoundedAttributes>(
    DecompoundedAttributes
) {

    override val items = listOf(
        item to json
    )

    companion object {

        val item =
            DecompoundedAttributes(
                QueryLanguage.German,
                attributeA,
                attributeB
            )
        val json = json {
            QueryLanguage.German.raw to jsonArray {
                +attributeA.raw
                +attributeB.raw
            }
        }
    }
}