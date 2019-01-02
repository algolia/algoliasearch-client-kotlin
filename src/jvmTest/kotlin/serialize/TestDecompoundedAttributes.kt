package serialize

import attributeA
import attributeB
import com.algolia.search.saas.data.DecompoundedAttributes
import com.algolia.search.saas.data.QueryLanguage
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestDecompoundedAttributes : TestSerializer<DecompoundedAttributes>(DecompoundedAttributes) {

    override val items = listOf(
        DecompoundedAttributes(QueryLanguage.German, attributeA, attributeB),
        DecompoundedAttributes(QueryLanguage.Finnish, attributeA, attributeB)
    )
}