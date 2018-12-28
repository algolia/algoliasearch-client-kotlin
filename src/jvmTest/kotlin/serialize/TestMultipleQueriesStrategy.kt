package serialize

import client.data.MultipleQueriesStrategy
import client.data.MultipleQueriesStrategy.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import tmp.TestSerializer
import unknown


@RunWith(JUnit4::class)
internal class TestMultipleQueriesStrategy : TestSerializer<MultipleQueriesStrategy>(
    MultipleQueriesStrategy,
    MultipleQueriesStrategy
) {

    override val item = listOf(
        None to JsonPrimitive(None.raw),
        StopIfEnoughMatches to JsonPrimitive(StopIfEnoughMatches.raw),
        Unknown(unknown) to JsonPrimitive(unknown)
    )
    override val items: List<Pair<List<MultipleQueriesStrategy>, JsonArray>> = listOf()
}