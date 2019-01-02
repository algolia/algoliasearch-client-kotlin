package serialize

import com.algolia.search.saas.data.BoundingBox
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestBoundingBox : TestSerializer<BoundingBox>(BoundingBox) {

    override val items = listOf(
        BoundingBox(1f, 2f, 3f, 4f)
    )
}