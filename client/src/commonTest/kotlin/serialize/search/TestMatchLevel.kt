package serialize.search

import com.algolia.search.model.search.MatchLevel
import com.algolia.search.model.search.MatchLevel.Full
import com.algolia.search.model.search.MatchLevel.None
import com.algolia.search.model.search.MatchLevel.Other
import com.algolia.search.model.search.MatchLevel.Partial
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestMatchLevel : TestSerializer<MatchLevel>(MatchLevel) {

    override val items = listOf(
        None to JsonPrimitive(None.raw),
        Partial to JsonPrimitive(Partial.raw),
        Full to JsonPrimitive(Full.raw),
        Other(unknown) to JsonPrimitive(unknown)
    )
}
