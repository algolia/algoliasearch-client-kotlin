package serialize

import attributeA
import attributeB
import com.algolia.search.saas.model.enums.DecompoundedAttributes
import com.algolia.search.saas.model.enums.QueryLanguage
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestDecompoundedAttributes : TestSerializer<DecompoundedAttributes>(
    DecompoundedAttributes
) {

    override val items = listOf(
        item to json
    )

    companion object {

        val item =
            DecompoundedAttributes(QueryLanguage.German, attributeA, attributeB)
        val json = json {
            QueryLanguage.German.raw to jsonArray {
                +attributeA.raw
                +attributeB.raw
            }
        }
    }
}