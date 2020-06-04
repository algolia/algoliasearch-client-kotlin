package serialize.synonym

import com.algolia.search.model.synonym.SynonymType
import com.algolia.search.serialize.KeyAlternativeCorrection1
import com.algolia.search.serialize.KeyAlternativeCorrection2
import com.algolia.search.serialize.KeyOneWaySynonym
import com.algolia.search.serialize.KeyPlaceholder
import com.algolia.search.serialize.KeySynonym
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown

internal class TestSynonymType : TestSerializer<SynonymType>(SynonymType) {

    override val items = listOf(
        SynonymType.OneWay to JsonLiteral(KeyOneWaySynonym),
        SynonymType.MultiWay to JsonLiteral(KeySynonym),
        SynonymType.AlternativeCorrections(SynonymType.Typo.One) to JsonLiteral(KeyAlternativeCorrection1),
        SynonymType.AlternativeCorrections(SynonymType.Typo.Two) to JsonLiteral(KeyAlternativeCorrection2),
        SynonymType.Placeholder to JsonLiteral(KeyPlaceholder),
        SynonymType.Other(unknown) to JsonLiteral(unknown)
    )
}
