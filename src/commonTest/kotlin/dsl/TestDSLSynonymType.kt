package dsl

import com.algolia.search.dsl.DSLSynonymType
import com.algolia.search.model.synonym.SynonymType
import shouldEqual
import kotlin.test.Test

internal class TestDSLSynonymType {

    @Test
    fun defaults() {
        val dsl = DSLSynonymType {
            +OneWay
            +MultiWay
            +Placeholder
            +AlternativeCorrectionsOneTypo
            +AlternativeCorrectionsTwoTypos
        }

        dsl shouldEqual listOf(
            SynonymType.OneWay,
            SynonymType.MultiWay,
            SynonymType.Placeholder,
            SynonymType.AlternativeCorrections(SynonymType.Typo.One),
            SynonymType.AlternativeCorrections(SynonymType.Typo.Two)
        )
    }

    @Test
    fun typos() {
        DSLSynonymType().apply {
            AlternativeCorrectionsOneTypo shouldEqual SynonymType.Typo.One
            AlternativeCorrectionsTwoTypos shouldEqual SynonymType.Typo.Two
        }
    }

    @Test
    fun synonymTypes() {
        DSLSynonymType().apply {
            OneWay shouldEqual SynonymType.OneWay
            MultiWay shouldEqual SynonymType.MultiWay
            Placeholder shouldEqual SynonymType.Placeholder
        }
    }
}
