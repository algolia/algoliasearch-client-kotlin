package serialize.rule

import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.Anchoring.Contains
import com.algolia.search.model.rule.Anchoring.EndsWith
import com.algolia.search.model.rule.Anchoring.Is
import com.algolia.search.model.rule.Anchoring.Other
import com.algolia.search.model.rule.Anchoring.StartsWith
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestAnchoring : TestSerializer<Anchoring>(Anchoring) {

    override val items = listOf(
        Is to JsonPrimitive(Key.Is),
        EndsWith to JsonPrimitive(Key.EndsWith),
        StartsWith to JsonPrimitive(Key.StartsWith),
        Contains to JsonPrimitive(Key.Contains),
        Other(unknown) to JsonPrimitive(unknown)
    )
}
