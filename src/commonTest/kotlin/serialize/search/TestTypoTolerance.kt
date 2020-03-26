package serialize.search

import com.algolia.search.model.search.TypoTolerance
import com.algolia.search.model.search.TypoTolerance.*
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown


internal class TestTypoTolerance : TestSerializer<TypoTolerance>(TypoTolerance) {

    override val items = listOf(
        True to JsonLiteral(true),
        False to JsonLiteral(false),
        Min to JsonLiteral(Min.raw),
        Strict to JsonLiteral(Strict.raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}