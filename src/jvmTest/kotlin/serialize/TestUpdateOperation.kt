package serialize

import attributeA
import com.algolia.search.saas.data.UpdateOperation
import com.algolia.search.saas.data.Value
import com.algolia.search.saas.serialize.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestUpdateOperation {

    @Test
    fun increment() {
        test(UpdateOperation.Increment(attributeA, 0), KeyIncrement)
    }

    @Test
    fun decrement() {
        test(UpdateOperation.Decrement(attributeA, 0), KeyDecrement)
    }

    @Test
    fun add() {
        test(UpdateOperation.Add(attributeA, 0), KeyAdd)
        test(UpdateOperation.Add(attributeA, "string"), KeyAdd)
    }

    @Test
    fun remove() {
        test(UpdateOperation.Remove(attributeA, 0), KeyRemove)
        test(UpdateOperation.Remove(attributeA, "string"), KeyRemove)
    }

    @Test
    fun addUnique() {
        test(UpdateOperation.AddUnique(attributeA, 0), KeyAddUnique)
        test(UpdateOperation.AddUnique(attributeA, "string"), KeyAddUnique)
    }

    private fun test(updateOperation: UpdateOperation, key: String) {
        val serialized = Json.plain.stringify(UpdateOperation, updateOperation)
        val deserialized = Json.plain.parseJson(serialized)

        deserialized shouldEqual json {
            Key_Operation to key
            when (val value = updateOperation.value) {
                is Value.String -> KeyValue to value.raw
                is Value.Number -> KeyValue to value.raw
            }
        }
    }
}