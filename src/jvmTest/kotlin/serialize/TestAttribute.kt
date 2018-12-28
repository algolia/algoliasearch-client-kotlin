package serialize

import attributeA
import attributes
import client.data.Attribute
import jsonAttributes
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestAttribute : TestSerializer<Attribute>(Attribute, Attribute) {

    override val item = listOf(
        attributeA to JsonPrimitive(attributeA.raw)
    )

    override val items = listOf(
        attributes to jsonAttributes
    )
}