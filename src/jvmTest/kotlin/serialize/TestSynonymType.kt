package serialize

import com.algolia.search.model.synonym.SynonymType
import com.algolia.search.serialize.*
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
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