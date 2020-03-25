package serialize.search

import com.algolia.search.model.search.AroundRadius
import com.algolia.search.model.search.AroundRadius.All
import com.algolia.search.model.search.AroundRadius.InMeters
import com.algolia.search.model.search.AroundRadius.Other
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown

internal class TestAroundRadius : TestSerializer<AroundRadius>(AroundRadius) {

    override val items = listOf(
        All to JsonLiteral(All.raw),
        InMeters(10) to JsonLiteral(10),
        Other(unknown) to JsonLiteral(unknown)
    )
}
