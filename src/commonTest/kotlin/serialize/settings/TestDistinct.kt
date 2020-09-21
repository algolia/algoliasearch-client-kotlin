package serialize.settings

import com.algolia.search.model.settings.Distinct
import com.algolia.search.serialize.internal.Json
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import shouldEqual
import kotlin.test.Test

internal class TestDistinct : TestSerializer<Distinct>(Distinct) {

    override val items = listOf(
        Distinct(0) to JsonPrimitive(0),
        Distinct(1) to JsonPrimitive(1),
        Distinct(2) to JsonPrimitive(2),
        Distinct(3) to JsonPrimitive(3)
    )

    @Test
    fun boolean() {
        Json.decodeFromJsonElement(Distinct, JsonPrimitive(false)) shouldEqual Distinct(0)
        Json.decodeFromJsonElement(Distinct, JsonPrimitive(true)) shouldEqual Distinct(1)
    }
}
