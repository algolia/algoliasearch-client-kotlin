package serialize.rule

import com.algolia.search.model.rule.Edit
import com.algolia.search.serialize.KeyDelete
import com.algolia.search.serialize.KeyInsert
import com.algolia.search.serialize.KeyRemove
import com.algolia.search.serialize.KeyReplace
import com.algolia.search.serialize.KeyType
import java.util.Locale
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer
import unknown

internal class TestEdit : TestSerializer<Edit>(Edit) {
    override val items = listOf(
        Edit(unknown) to buildJsonObject {
            put(KeyType, KeyRemove.lowercase())
            put(KeyDelete, unknown)
        },
        Edit(unknown, unknown) to buildJsonObject {
            put(KeyType, KeyReplace)
            put(KeyDelete, unknown)
            put(KeyInsert, unknown)
        }
    )
}
