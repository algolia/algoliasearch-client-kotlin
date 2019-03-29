package serialize.settings

import com.algolia.search.model.settings.AdvancedSyntaxFeatures
import com.algolia.search.model.settings.AdvancedSyntaxFeatures.*
import com.algolia.search.serialize.KeyExactPhrase
import com.algolia.search.serialize.KeyExcludeWords
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown


internal class TestAdvancedSyntaxFeatures : TestSerializer<AdvancedSyntaxFeatures>(AdvancedSyntaxFeatures) {

    override val items = listOf(
        ExcludeWords to JsonLiteral(KeyExcludeWords),
        ExactPhrase to JsonLiteral(KeyExactPhrase),
        Other(unknown) to JsonLiteral(unknown)
    )
}