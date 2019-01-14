package serialize

import com.algolia.search.saas.data.BoundingBox
import com.algolia.search.saas.to
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestBoundingBox : TestSerializer<BoundingBox>(BoundingBox) {

    override val items = listOf(
        boundingBox to json
    )

    companion object {

        val boundingBox = BoundingBox(1f to 2f, 3f to 4f)
        val json = JsonLiteral((1..4).joinToString(",") { "$it.0" })
    }
}