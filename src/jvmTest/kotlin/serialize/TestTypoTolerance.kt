package serialize

import boolean
import client.data.TypoTolerance
import client.data.TypoTolerance.*
import client.data.TypoTolerance.Boolean
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import tmp.TestSerializer
import unknown


@RunWith(JUnit4::class)
internal class TestTypoTolerance : TestSerializer<TypoTolerance>(TypoTolerance, TypoTolerance) {

    override val item = listOf(
        Boolean(boolean) to JsonPrimitive(boolean),
        Min to JsonPrimitive(Min.raw),
        Strict to JsonPrimitive(Strict.raw),
        Unknown(unknown) to JsonPrimitive(unknown)
    )
    override val items: List<Pair<List<TypoTolerance>, JsonArray>> = listOf()
}