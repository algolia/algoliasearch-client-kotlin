package serialize

import attributeA
import com.algolia.search.saas.data.Attribute
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestAttribute : TestSerializer<Attribute>(Attribute) {

    override val items = listOf(
        attributeA
    )
}