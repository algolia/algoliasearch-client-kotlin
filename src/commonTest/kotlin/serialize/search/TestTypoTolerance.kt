package serialize.search

import boolean
import com.algolia.search.model.search.TypoTolerance
import com.algolia.search.model.search.TypoTolerance.*
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown


internal class TestTypoTolerance : TestSerializer<TypoTolerance>(TypoTolerance) {

    override val items = listOf(
        Boolean(boolean) to JsonLiteral(boolean),
        Min to JsonLiteral(Min.raw),
        Strict to JsonLiteral(Strict.raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}