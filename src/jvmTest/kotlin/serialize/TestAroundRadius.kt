package serialize

import client.data.AroundRadius
import client.data.AroundRadius.*
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestAroundRadius : TestSerializer<AroundRadius>(AroundRadius) {

    override val item = listOf(
        All to JsonPrimitive(All.raw),
        InMeters(10) to JsonPrimitive(10),
        Unknown(unknown) to JsonPrimitive(unknown)
    )
}