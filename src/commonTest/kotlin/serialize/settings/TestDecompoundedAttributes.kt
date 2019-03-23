package serialize.settings

import attributeA
import attributeB
import com.algolia.search.model.search.QueryLanguage
import com.algolia.search.model.settings.DecompoundedAttributes
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import serialize.TestSerializer


internal class TestDecompoundedAttributes : TestSerializer<DecompoundedAttributes>(
    DecompoundedAttributes
) {

    override val items = listOf(
        item to json,
        DecompoundedAttributes(
            QueryLanguage.Dutch,
            attributeA,
            attributeB
        ) to json {
            QueryLanguage.Dutch.raw to jsonArray {
                +attributeA.raw
                +attributeB.raw
            }
        },
        DecompoundedAttributes(
            QueryLanguage.Finnish,
            attributeA,
            attributeB
        ) to json {
            QueryLanguage.Finnish.raw to jsonArray {
                +attributeA.raw
                +attributeB.raw
            }
        }
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