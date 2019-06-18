package serialize.settings

import attributeA
import attributeB
import com.algolia.search.model.search.Language
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
            Language.Dutch,
            attributeA,
            attributeB
        ) to json {
            Language.Dutch.raw to jsonArray {
                +attributeA.raw
                +attributeB.raw
            }
        },
        DecompoundedAttributes(
            Language.Finnish,
            attributeA,
            attributeB
        ) to json {
            Language.Finnish.raw to jsonArray {
                +attributeA.raw
                +attributeB.raw
            }
        }
    )

    companion object {

        val item =
            DecompoundedAttributes(
                Language.German,
                attributeA,
                attributeB
            )
        val json = json {
            Language.German.raw to jsonArray {
                +attributeA.raw
                +attributeB.raw
            }
        }
    }
}