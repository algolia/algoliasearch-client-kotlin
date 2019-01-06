package serialize

import attributeA
import com.algolia.search.saas.data.UpdateOperation
import com.algolia.search.saas.data.Value
import com.algolia.search.saas.serialize.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestUpdateOperation : TestSerializer<UpdateOperation>(UpdateOperation) {

    private val increment = UpdateOperation.Increment(attributeA, 0)
    private val decrement = UpdateOperation.Decrement(attributeA, 0)
    private val add = UpdateOperation.Add(attributeA, "value")
    private val remove = UpdateOperation.Remove(attributeA, "value")
    private val addUnique = UpdateOperation.AddUnique(attributeA, "value")

    override val items = listOf(
        increment to toJson(increment),
        decrement to toJson(decrement),
        add to toJson(add),
        remove to toJson(remove),
        addUnique to toJson(addUnique)
    )

    private fun toJson(updateOperation: UpdateOperation): JsonElement {
        val key = when (updateOperation) {
            is UpdateOperation.Increment -> KeyIncrement
            is UpdateOperation.Decrement -> KeyDecrement
            is UpdateOperation.Add -> KeyAdd
            is UpdateOperation.Remove -> KeyRemove
            is UpdateOperation.AddUnique -> KeyAddUnique
        }
        return json {
            updateOperation.attribute.raw to json {
                Key_Operation to key
                when (val value = updateOperation.value) {
                    is Value.String -> KeyValue to value.raw
                    is Value.Number -> KeyValue to value.raw
                }
            }
        }
    }
}