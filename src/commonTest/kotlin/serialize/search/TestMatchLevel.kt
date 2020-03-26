package serialize.search

import com.algolia.search.model.search.MatchLevel
import com.algolia.search.model.search.MatchLevel.*
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown


internal class TestMatchLevel : TestSerializer<MatchLevel>(MatchLevel) {

    override val items = listOf(
        None to JsonLiteral(None.raw),
        Partial to JsonLiteral(Partial.raw),
        Full to JsonLiteral(Full.raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}