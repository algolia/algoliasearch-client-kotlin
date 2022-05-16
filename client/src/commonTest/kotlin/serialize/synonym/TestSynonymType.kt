package serialize.synonym

import com.algolia.search.model.synonym.SynonymType
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestSynonymType : TestSerializer<SynonymType>(SynonymType) {

    override val items = listOf(
        SynonymType.OneWay to JsonPrimitive(Key.OneWaySynonym),
        SynonymType.MultiWay to JsonPrimitive(Key.Synonym),
        SynonymType.AlternativeCorrections(SynonymType.Typo.One) to JsonPrimitive(Key.AlternativeCorrection1),
        SynonymType.AlternativeCorrections(SynonymType.Typo.Two) to JsonPrimitive(Key.AlternativeCorrection2),
        SynonymType.Placeholder to JsonPrimitive(Key.Placeholder),
        SynonymType.Other(unknown) to JsonPrimitive(unknown)
    )
}
