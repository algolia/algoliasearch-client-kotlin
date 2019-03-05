package serialize.serializer

import com.algolia.search.model.search.Point
import com.algolia.search.serialize.KSerializerPoint
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer


@RunWith(JUnit4::class)
internal class TestKSerializerPoint : TestSerializer<Point>(KSerializerPoint) {

    override val items = listOf(
        Point(0.0f, 0.0f) to JsonLiteral("0.0,0.0"),
        Point(-4.0f, 5.0f) to JsonLiteral("-4.0,5.0")
    )
}