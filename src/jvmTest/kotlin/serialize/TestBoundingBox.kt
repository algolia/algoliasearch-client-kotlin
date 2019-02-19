package serialize

import com.algolia.search.model.search.BoundingBox
import com.algolia.search.and
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestBoundingBox : TestSerializer<BoundingBox>(BoundingBox) {

    override val items = listOf(
        boundingBox to json
    )

    companion object {

        val boundingBox = BoundingBox(1f and 2f, 3f and 4f)
        val json = jsonArray {
            +(1f as Number)
            +(2f as Number)
            +(3f as Number)
            +(4f as Number)
        }
    }
}