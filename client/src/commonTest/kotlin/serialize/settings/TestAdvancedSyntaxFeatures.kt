package serialize.settings

import com.algolia.search.model.settings.AdvancedSyntaxFeatures
import com.algolia.search.model.settings.AdvancedSyntaxFeatures.ExactPhrase
import com.algolia.search.model.settings.AdvancedSyntaxFeatures.ExcludeWords
import com.algolia.search.model.settings.AdvancedSyntaxFeatures.Other
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestAdvancedSyntaxFeatures : TestSerializer<AdvancedSyntaxFeatures>(AdvancedSyntaxFeatures) {

    override val items = listOf(
        ExcludeWords to JsonPrimitive(Key.ExcludeWords),
        ExactPhrase to JsonPrimitive(Key.ExactPhrase),
        Other(unknown) to JsonPrimitive(unknown)
    )
}
