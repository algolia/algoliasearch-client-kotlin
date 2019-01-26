package serialize

import attributeA
import com.algolia.search.saas.model.indexing.PartialUpdate
import com.algolia.search.saas.model.indexing.Value
import com.algolia.search.saas.serialize.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestPartialUpdate : TestSerializer<PartialUpdate>(PartialUpdate) {

    private val increment = PartialUpdate.Increment(attributeA, 0)
    private val decrement = PartialUpdate.Decrement(attributeA, 0)
    private val add = PartialUpdate.Add(attributeA, "value")
    private val remove = PartialUpdate.Remove(attributeA, "value")
    private val addUnique = PartialUpdate.AddUnique(attributeA, "value")

    override val items = listOf(
        increment to toJson(increment),
        decrement to toJson(decrement),
        add to toJson(add),
        remove to toJson(remove),
        addUnique to toJson(addUnique)
    )

    private fun toJson(partialUpdate: PartialUpdate): JsonElement {
        val key = when (partialUpdate) {
            is PartialUpdate.Increment -> KeyIncrement
            is PartialUpdate.Decrement -> KeyDecrement
            is PartialUpdate.Add -> KeyAdd
            is PartialUpdate.Remove -> KeyRemove
            is PartialUpdate.AddUnique -> KeyAddUnique
        }
        return json {
            partialUpdate.attribute.raw to json {
                Key_Operation to key
                when (val value = partialUpdate.value) {
                    is Value.String -> KeyValue to value.raw
                    is Value.Number -> KeyValue to value.raw
                }
            }
        }
    }
}