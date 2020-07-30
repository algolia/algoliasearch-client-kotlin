package serialize.search

import com.algolia.search.model.search.TypoTolerance
import com.algolia.search.model.search.TypoTolerance.False
import com.algolia.search.model.search.TypoTolerance.Min
import com.algolia.search.model.search.TypoTolerance.Other
import com.algolia.search.model.search.TypoTolerance.Strict
import com.algolia.search.model.search.TypoTolerance.True
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestTypoTolerance : TestSerializer<TypoTolerance>(TypoTolerance) {

    override val items = listOf(
        True to JsonPrimitive(true),
        False to JsonPrimitive(false),
        Min to JsonPrimitive(Min.raw),
        Strict to JsonPrimitive(Strict.raw),
        Other(unknown) to JsonPrimitive(unknown)
    )
}
