package serialize.indexing

import attributeA
import com.algolia.search.model.indexing.PartialUpdate
import com.algolia.search.serialize.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.json
import serialize.TestSerializer


internal class TestPartialUpdate : TestSerializer<PartialUpdate>(PartialUpdate) {

    private val increment = PartialUpdate.Increment(attributeA, 0)
    private val decrement = PartialUpdate.Decrement(attributeA, 0)
    private val addString = PartialUpdate.Add(attributeA, "value")
    private val addNumber = PartialUpdate.Add(attributeA, 0)
    private val removeString = PartialUpdate.Remove(attributeA, "value")
    private val removeNumber = PartialUpdate.Remove(attributeA, 0)
    private val addUniqueString = PartialUpdate.AddUnique(attributeA, "value")
    private val addUniqueNumber = PartialUpdate.AddUnique(attributeA, 0)

    override val items = listOf(
        increment to toJson(increment),
        decrement to toJson(decrement),
        addString to toJson(addString),
        addNumber to toJson(addNumber),
        removeNumber to toJson(removeNumber),
        removeString to toJson(removeString),
        addUniqueString to toJson(addUniqueString),
        addUniqueNumber to toJson(addUniqueNumber)
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
                    is PartialUpdate.Value.String -> KeyValue to value.raw
                    is PartialUpdate.Value.Number -> KeyValue to value.raw
                }
            }
        }
    }
}