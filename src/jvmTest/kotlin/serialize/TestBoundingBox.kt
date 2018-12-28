package serialize

import client.data.BoundingBox
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestBoundingBox : TestSerializer<BoundingBox>(BoundingBox, BoundingBox) {

    private val boundingBox = BoundingBox(1f, 2f, 3f, 4f)
    private val jsonArray = jsonArray {
        +(1f as Number)
        +(2f as Number)
        +(3f as Number)
        +(4f as Number)
    }

    override
    val item = listOf(
        boundingBox to jsonArray
    )
    override val items = listOf(
        listOf(
            boundingBox,
            boundingBox
        ) to jsonArray {
            +jsonArray
            +jsonArray
        }
    )
}