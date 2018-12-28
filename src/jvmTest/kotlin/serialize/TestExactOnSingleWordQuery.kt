package serialize

import client.data.ExactOnSingleWordQuery
import client.data.ExactOnSingleWordQuery.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import tmp.TestSerializer
import unknown


@RunWith(JUnit4::class)
internal class TestExactOnSingleWordQuery : TestSerializer<ExactOnSingleWordQuery>() {

    override val serializer = ExactOnSingleWordQuery
    override val item = listOf(
        Attribute to JsonPrimitive(Attribute.raw),
        None to JsonPrimitive(None.raw),
        Word to JsonPrimitive(Word.raw),
        Unknown(unknown) to JsonPrimitive(unknown)
    )
    override val items: List<Pair<List<ExactOnSingleWordQuery>, JsonArray>> = listOf()
}