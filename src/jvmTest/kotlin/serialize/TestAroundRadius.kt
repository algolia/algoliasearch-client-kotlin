package serialize

import com.algolia.search.model.search.AroundRadius
import com.algolia.search.model.search.AroundRadius.*
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestAroundRadius : TestSerializer<AroundRadius>(AroundRadius) {

    override val items = listOf(
        All to JsonLiteral(All.raw),
        InMeters(10) to JsonLiteral(10),
        Other(unknown) to JsonLiteral(unknown)
    )
}