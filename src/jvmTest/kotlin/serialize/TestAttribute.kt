package serialize

import attributeA
import attributeB
import client.data.Attribute
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import tmp.TestSerializer


@RunWith(JUnit4::class)
internal class TestAttribute : TestSerializer<Attribute>(Attribute) {

    override val item = listOf(
        attributeA to JsonPrimitive(attributeA.name)
    )

    override val items = listOf(
        listOf(
            attributeA,
            attributeB
        ) to jsonArray {
            +attributeA.name
            +attributeB.name
        }
    )
}