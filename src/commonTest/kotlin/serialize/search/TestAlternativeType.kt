package serialize.search

import com.algolia.search.model.search.AlternativeType
import com.algolia.search.model.search.AlternativeType.*
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