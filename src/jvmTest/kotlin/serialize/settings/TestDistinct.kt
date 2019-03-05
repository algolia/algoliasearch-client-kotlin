package serialize.settings

import com.algolia.search.model.settings.Distinct
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer


@RunWith(JUnit4::class)
internal class TestDistinct : TestSerializer<Distinct>(Distinct) {

    override val items = listOf(
        Distinct.True to JsonLiteral(true),
        Distinct.False to JsonLiteral(false),
        Distinct.Other(2) to JsonLiteral(2),
        Distinct.Other(3) to JsonLiteral(3)
    )
}