package serialize.rule

import com.algolia.search.model.rule.Edit
import com.algolia.search.serialize.internal.KeyDelete
import com.algolia.search.serialize.internal.KeyInsert
import com.algolia.search.serialize.internal.KeyRemove
import com.algolia.search.serialize.internal.KeyReplace
import com.algolia.search.serialize.internal.KeyType
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
