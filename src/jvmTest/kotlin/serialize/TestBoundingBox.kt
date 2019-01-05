package serialize

import com.algolia.search.saas.data.BoundingBox
import com.algolia.search.saas.to
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestBoundingBox : TestSerializer<BoundingBox>(BoundingBox) {

    override val items = listOf(
        BoundingBox(1f to 2f, 3f to 4f)
    )
}