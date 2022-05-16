package serialize.rule

import com.algolia.search.model.rule.Edit
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer
import unknown

internal class TestEdit : TestSerializer<Edit>(Edit) {
    override val items = listOf(
        Edit(unknown) to buildJsonObject {
            put(Key.Type, Key.Remove.lowercase())
            put(Key.Delete, unknown)
        },
        Edit(unknown, unknown) to buildJsonObject {
            put(Key.Type, Key.Replace)
            put(Key.Delete, unknown)
            put(Key.Insert, unknown)
        }
    )
}
