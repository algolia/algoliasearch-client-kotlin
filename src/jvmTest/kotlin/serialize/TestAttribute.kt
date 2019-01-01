package serialize

import attributeA
import com.algolia.search.saas.data.Attribute
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestAttribute : TestSerializer<Attribute>(Attribute) {

    override val item = listOf(
        attributeA to JsonPrimitive(attributeA.raw)
    )
}