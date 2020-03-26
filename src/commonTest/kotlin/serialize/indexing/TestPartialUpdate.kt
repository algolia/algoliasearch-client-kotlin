package serialize.indexing

import attributeA
import com.algolia.search.model.indexing.Partial
import com.algolia.search.serialize.KeyAdd
import com.algolia.search.serialize.KeyAddUnique
import com.algolia.search.serialize.KeyDecrement
import com.algolia.search.serialize.KeyIncrement
import com.algolia.search.serialize.KeyRemove
import com.algolia.search.serialize.KeyValue
import com.algolia.search.serialize.Key_Operation
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import serialize.TestSerializer

internal class TestPartialUpdate : TestSerializer<Partial>(Partial) {

    private val updateString = Partial.Update(attributeA, "value")
    private val updateNumber = Partial.Update(attributeA, 0)
    private val updateArray = Partial.Update(attributeA, jsonArray { +0 })
    private val updateObject = Partial.Update(attributeA, json { "key" to "value" })
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
        updateObject to toJson(updateObject),
        updateArray to toJson(updateArray),
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
                KeyValue to partial.value
            }
        }
    }
}
