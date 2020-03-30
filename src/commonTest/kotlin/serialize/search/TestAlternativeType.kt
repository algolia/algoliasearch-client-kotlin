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
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown

internal class TestAlternativeType : TestSerializer<AlternativeType>(AlternativeType) {

    override val items = listOf(
        Original to JsonLiteral(Original.raw),
        Excluded to JsonLiteral(Excluded.raw),
        Optional to JsonLiteral(Optional.raw),
        StopWord to JsonLiteral(StopWord.raw),
        Typo to JsonLiteral(Typo.raw),
        Split to JsonLiteral(Split.raw),
        Concat to JsonLiteral(Concat.raw),
        Synonym to JsonLiteral(Synonym.raw),
        AlternativeCorrection to JsonLiteral(AlternativeCorrection.raw),
        Plural to JsonLiteral(Plural.raw),
        Compound to JsonLiteral(Compound.raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}
