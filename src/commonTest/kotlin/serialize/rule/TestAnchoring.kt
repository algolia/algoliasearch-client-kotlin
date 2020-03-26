package serialize.rule

import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.Anchoring.Contains
import com.algolia.search.model.rule.Anchoring.EndsWith
import com.algolia.search.model.rule.Anchoring.Is
import com.algolia.search.model.rule.Anchoring.Other
import com.algolia.search.model.rule.Anchoring.StartsWith
import com.algolia.search.serialize.KeyContains
import com.algolia.search.serialize.KeyEndsWith
import com.algolia.search.serialize.KeyIs
import com.algolia.search.serialize.KeyStartsWith
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown

internal class TestAnchoring : TestSerializer<Anchoring>(Anchoring) {

    override val items = listOf(
        Is to JsonLiteral(KeyIs),
        EndsWith to JsonLiteral(KeyEndsWith),
        StartsWith to JsonLiteral(KeyStartsWith),
        Contains to JsonLiteral(KeyContains),
        Other(unknown) to JsonLiteral(unknown)
    )
}
