package serialize

import attributeA
import attributeB
import client.data.DecompoundedAttributes
import client.data.QueryLanguage
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import tmp.TestSerializer


@RunWith(JUnit4::class)
internal class TestDecompoundedAttributes : TestSerializer<DecompoundedAttributes>(
    DecompoundedAttributes,
    DecompoundedAttributes
) {

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
    override val items = listOf(
        listOf(
            decompoundedDe,
            decompoundedFi
        ) to jsonArray {
            +jsonDe
            +jsonFi
        }
    )
}