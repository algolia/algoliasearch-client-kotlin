package dsl.strategy

import com.algolia.search.dsl.strategy.DSLAdvancedSyntaxFeatures
import com.algolia.search.model.settings.AdvancedSyntaxFeatures
import shouldEqual
import kotlin.test.Test

internal class TestDSLAdvancedSyntaxFeatures {

    @Test
    fun default() {
        val dsl = DSLAdvancedSyntaxFeatures {
            +ExcludeWords
            +ExactPhrase
        }

        dsl shouldEqual listOf(
            AdvancedSyntaxFeatures.ExcludeWords,
            AdvancedSyntaxFeatures.ExactPhrase
        )
    }

    @Test
    fun exact() {
        DSLAdvancedSyntaxFeatures().apply {
            ExcludeWords shouldEqual AdvancedSyntaxFeatures.ExcludeWords
            ExactPhrase shouldEqual AdvancedSyntaxFeatures.ExactPhrase
        }
    }
}
