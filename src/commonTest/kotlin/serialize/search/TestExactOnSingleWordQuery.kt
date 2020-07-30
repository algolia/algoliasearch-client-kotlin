package serialize.search

import com.algolia.search.model.search.ExactOnSingleWordQuery
import com.algolia.search.model.search.ExactOnSingleWordQuery.Attribute
import com.algolia.search.model.search.ExactOnSingleWordQuery.None
import com.algolia.search.model.search.ExactOnSingleWordQuery.Other
import com.algolia.search.model.search.ExactOnSingleWordQuery.Word
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestExactOnSingleWordQuery : TestSerializer<ExactOnSingleWordQuery>(
    ExactOnSingleWordQuery
) {

    override val items = listOf(
        Attribute to JsonPrimitive(Attribute.raw),
        None to JsonPrimitive(None.raw),
        Word to JsonPrimitive(Word.raw),
        Other(unknown) to JsonPrimitive(unknown)
    )
}
