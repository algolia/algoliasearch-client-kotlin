package serialize.indexing

import attributeA
import com.algolia.search.model.indexing.Partial
import com.algolia.search.serialize.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.json
import serialize.TestSerializer


internal class TestPartialUpdate : TestSerializer<Partial>(Partial) {

    private val updateString = Partial.Update(attributeA, "value")
    private val updateNumber = Partial.Update(attributeA, 0)
    private val increment = Partial.Increment(attributeA, 0)
    private val decrement = Partial.Decrement(attributeA, 0)
    private val addString = Partial.Add(attributeA, "value")
    private val addNumber = Partial.Add(attributeA, 0)
    private val removeString = Partial.Remove(attributeA, "value")
    private val removeNumber = Partial.Remove(attributeA, 0)
    private val addUniqueString = Partial.AddUnique(attributeA, "value")
    private val addUniqueNumber = Partial.AddUnique(attributeA, 0)

    override val items = listOf(
        updateString to toJson(updateString),
        updateNumber to toJson(updateNumber),
        increment to toJson(increment),
        decrement to toJson(decrement),
        addString to toJson(addString),
        addNumber to toJson(addNumber),
        removeNumber to toJson(removeNumber),
        removeString to toJson(removeString),
        addUniqueString to toJson(addUniqueString),
        addUniqueNumber to toJson(addUniqueNumber)
    )

    private fun toJson(partial: Partial): JsonElement {
        val key = when (partial) {
            is Partial.Update -> null
            is Partial.Increment -> KeyIncrement
            is Partial.Decrement -> KeyDecrement
            is Partial.Add -> KeyAdd
            is Partial.Remove -> KeyRemove
            is Partial.AddUnique -> KeyAddUnique
        }
        return json {
            partial.attribute.raw to json {
                key?.let { Key_Operation to key }
                when (val value = partial.value) {
                    is Partial.Value.String -> KeyValue to value.raw
                    is Partial.Value.Number -> KeyValue to value.raw
                }
            }
        }
    }
}