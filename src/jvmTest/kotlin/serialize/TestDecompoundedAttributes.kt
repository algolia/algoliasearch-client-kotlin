package serialize

import attributeA
import attributeB
import com.algolia.search.saas.data.DecompoundedAttributes
import com.algolia.search.saas.data.QueryLanguage
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestDecompoundedAttributes : TestSerializer<DecompoundedAttributes>(DecompoundedAttributes) {

    private val decompoundedDe = DecompoundedAttributes(QueryLanguage.German, attributeA, attributeB)
    private val decompoundedFi = DecompoundedAttributes(QueryLanguage.Finnish, attributeA, attributeB)
    private val jsonArray = jsonArray {
        +attributeA.raw
        +attributeB.raw
    }
    private val jsonDe = json {
        QueryLanguage.German.raw to jsonArray
    }
    private val jsonFi = json {
        QueryLanguage.Finnish.raw to jsonArray
    }

    override val item = listOf(
        decompoundedDe to jsonDe,
        decompoundedFi to jsonFi
    )
}