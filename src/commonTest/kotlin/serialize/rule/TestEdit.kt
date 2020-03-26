package serialize.rule

import com.algolia.search.model.rule.Edit
import com.algolia.search.serialize.KeyDelete
import com.algolia.search.serialize.KeyInsert
import com.algolia.search.serialize.KeyRemove
import com.algolia.search.serialize.KeyReplace
import com.algolia.search.serialize.KeyType
import kotlinx.serialization.json.json
import serialize.TestSerializer
import unknown

internal class TestEdit : TestSerializer<Edit>(Edit) {
    override val items = listOf(
        Edit(unknown) to json {
            KeyType to KeyRemove.toLowerCase()
            KeyDelete to unknown
        },
        Edit(unknown, unknown) to json {
            KeyType to KeyReplace
            KeyDelete to unknown
            KeyInsert to unknown
        }
    )
}
