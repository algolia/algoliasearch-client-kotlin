package serialize.settings

import com.algolia.search.model.settings.Distinct
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import shouldEqual
import kotlin.test.Test


internal class TestDistinct : TestSerializer<Distinct>(Distinct) {

    override val items = listOf(
        Distinct(0) to JsonLiteral(0),
        Distinct(1) to JsonLiteral(1),
        Distinct(2) to JsonLiteral(2),
        Distinct(3) to JsonLiteral(3)
    )

    @Test
    fun boolean() {
        Json.plain.fromJson(Distinct, JsonLiteral(false)) shouldEqual Distinct(0)
        Json.plain.fromJson(Distinct, JsonLiteral(true)) shouldEqual Distinct(1)
    }
}