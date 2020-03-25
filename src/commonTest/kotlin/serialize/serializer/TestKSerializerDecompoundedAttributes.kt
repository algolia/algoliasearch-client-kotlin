package serialize.serializer

import attributeA
import attributeB
import com.algolia.search.model.search.Language
import com.algolia.search.model.settings.DecompoundedAttributes
import com.algolia.search.serialize.KSerializerDecompoundedAttributes
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import serialize.TestSerializer

internal class TestKSerializerDecompoundedAttributes :
    TestSerializer<List<DecompoundedAttributes>>(KSerializerDecompoundedAttributes) {

    override val items = listOf(
        listOf(DecompoundedAttributes(Language.German, listOf(attributeA, attributeB))) to json {
            Language.German.raw to jsonArray {
                +attributeA.raw
                +attributeB.raw
            }
        }
    )
}
