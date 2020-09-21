package serialize.serializer

import attributeA
import attributeB
import com.algolia.search.model.search.Language
import com.algolia.search.model.settings.DecompoundedAttributes
import com.algolia.search.serialize.KSerializerDecompoundedAttributes
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import serialize.TestSerializer

internal class TestKSerializerDecompoundedAttributes :
    TestSerializer<List<DecompoundedAttributes>>(KSerializerDecompoundedAttributes) {

    override val items = listOf(
        listOf(DecompoundedAttributes(Language.German, listOf(attributeA, attributeB))) to buildJsonObject {
            put(
                Language.German.raw,
                buildJsonArray {
                    add(attributeA.raw)
                    add(attributeB.raw)
                }
            )
        }
    )
}
