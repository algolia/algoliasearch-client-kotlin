package com.algolia.client.dsl.live

import com.algolia.client.dsl.cases.EscapingCases
import com.algolia.client.dsl.live.testkit.ESCAPING_FIXTURE
import com.algolia.client.dsl.live.testkit.LiveIndex
import com.algolia.client.dsl.live.testkit.assertCase
import com.algolia.client.dsl.live.testkit.assertDelete
import com.algolia.client.dsl.live.testkit.live
import kotlin.test.Test
import kotlin.time.Duration.Companion.seconds
import org.junit.AfterClass
import org.junit.BeforeClass

internal class EscapingLiveTest {

  companion object {
    private lateinit var fx: LiveIndex

    @JvmStatic
    @BeforeClass
    fun createFixture() {
      fx = LiveIndex.create("escaping", ESCAPING_FIXTURE)
    }

    @JvmStatic
    @AfterClass
    fun deleteFixture() {
      if (::fx.isInitialized) fx.close()
    }
  }

  @Test fun parenthesesQuoted() = live { fx.assertCase(EscapingCases.parenthesesQuoted) }

  @Test fun colonQuoted() = live { fx.assertCase(EscapingCases.colonQuoted) }

  @Test fun lessThanQuoted() = live { fx.assertCase(EscapingCases.lessThanQuoted) }

  @Test fun equalsQuoted() = live { fx.assertCase(EscapingCases.equalsQuoted) }

  @Test fun exclamationQuoted() = live { fx.assertCase(EscapingCases.exclamationQuoted) }

  @Test fun greaterThanQuoted() = live { fx.assertCase(EscapingCases.greaterThanQuoted) }

  @Test fun embeddedQuoteEscaped() = live { fx.assertCase(EscapingCases.embeddedQuoteEscaped) }

  @Test fun innerBackslashEscaped() = live { fx.assertCase(EscapingCases.innerBackslashEscaped) }

  @Test
  fun trailingBackslashEscaped() = live { fx.assertCase(EscapingCases.trailingBackslashEscaped) }

  @Test
  fun backslashBeforeSpaceEscaped() = live {
    fx.assertCase(EscapingCases.backslashBeforeSpaceEscaped)
  }

  @Test fun dottedValueBare() = live { fx.assertCase(EscapingCases.dottedValueBare) }

  @Test fun whitespaceValueQuoted() = live { fx.assertCase(EscapingCases.whitespaceValueQuoted) }

  @Test fun keywordValuesQuoted() = live { fx.assertCase(EscapingCases.keywordValuesQuoted) }

  @Test fun toKeywordQuoted() = live { fx.assertCase(EscapingCases.toKeywordQuoted) }

  @Test fun nonAsciiQuoted() = live { fx.assertCase(EscapingCases.nonAsciiQuoted) }

  @Test fun safeCharactersBare() = live { fx.assertCase(EscapingCases.safeCharactersBare) }

  @Test fun tagWithQuotesEscaped() = live { fx.assertCase(EscapingCases.tagWithQuotesEscaped) }

  @Test
  fun attributeWithColonQuoted() = live { fx.assertCase(EscapingCases.attributeWithColonQuoted) }

  @Test
  fun attributeWithSpaceQuoted() = live { fx.assertCase(EscapingCases.attributeWithSpaceQuoted) }

  @Test
  fun numericAttributesQuoted() = live { fx.assertCase(EscapingCases.numericAttributesQuoted) }

  @Test fun tagsQuoted() = live { fx.assertCase(EscapingCases.tagsQuoted) }

  @Test
  fun deleteByQuotedValues() =
    live(timeout = 180.seconds) {
      fx.assertDelete(EscapingCases.deleteByQuotedValues) { copy ->
        copy.client.deleteBy(copy.name, EscapingCases.deleteByQuotedValues.dsl()).taskID
      }
    }
}
