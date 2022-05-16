package serialize.synonym

import com.algolia.search.model.synonym.SynonymType
import com.algolia.search.serialize.internal.KeyAlternativeCorrection1
import com.algolia.search.serialize.internal.KeyAlternativeCorrection2
import com.algolia.search.serialize.internal.KeyOneWaySynonym
import com.algolia.search.serialize.internal.KeyPlaceholder
import com.algolia.search.serialize.internal.KeySynonym
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestSynonymType : TestSerializer<SynonymType>(SynonymType) {

    override val items = listOf(
        SynonymType.OneWay to JsonPrimitive(KeyOneWaySynonym),
        SynonymType.MultiWay to JsonPrimitive(KeySynonym),
        SynonymType.AlternativeCorrections(SynonymType.Typo.One) to JsonPrimitive(KeyAlternativeCorrection1),
        SynonymType.AlternativeCorrections(SynonymType.Typo.Two) to JsonPrimitive(KeyAlternativeCorrection2),
        SynonymType.Placeholder to JsonPrimitive(KeyPlaceholder),
        SynonymType.Other(unknown) to JsonPrimitive(unknown)
    )
}
