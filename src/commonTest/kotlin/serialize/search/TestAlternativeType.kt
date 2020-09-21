package serialize.search

import com.algolia.search.model.search.AlternativeType
import com.algolia.search.model.search.AlternativeType.AlternativeCorrection
import com.algolia.search.model.search.AlternativeType.Compound
import com.algolia.search.model.search.AlternativeType.Concat
import com.algolia.search.model.search.AlternativeType.Excluded
import com.algolia.search.model.search.AlternativeType.Optional
import com.algolia.search.model.search.AlternativeType.Original
import com.algolia.search.model.search.AlternativeType.Other
import com.algolia.search.model.search.AlternativeType.Plural
import com.algolia.search.model.search.AlternativeType.Split
import com.algolia.search.model.search.AlternativeType.StopWord
import com.algolia.search.model.search.AlternativeType.Synonym
import com.algolia.search.model.search.AlternativeType.Typo
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestAlternativeType : TestSerializer<AlternativeType>(AlternativeType) {

    override val items = listOf(
        Original to JsonPrimitive(Original.raw),
        Excluded to JsonPrimitive(Excluded.raw),
        Optional to JsonPrimitive(Optional.raw),
        StopWord to JsonPrimitive(StopWord.raw),
        Typo to JsonPrimitive(Typo.raw),
        Split to JsonPrimitive(Split.raw),
        Concat to JsonPrimitive(Concat.raw),
        Synonym to JsonPrimitive(Synonym.raw),
        AlternativeCorrection to JsonPrimitive(AlternativeCorrection.raw),
        Plural to JsonPrimitive(Plural.raw),
        Compound to JsonPrimitive(Compound.raw),
        Other(unknown) to JsonPrimitive(unknown)
    )
}
