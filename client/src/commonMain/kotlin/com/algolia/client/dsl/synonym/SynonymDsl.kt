@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl.synonym

import com.algolia.client.dsl.AlgoliaExperimentalDsl
import com.algolia.client.dsl.DSLStrings
import com.algolia.client.dsl.generated.DSLSynonymHit
import com.algolia.client.model.search.SynonymHit
import com.algolia.client.model.search.SynonymType

/** Constructs a [SynonymHit] from a [DSLSynonymHit] block. Last write wins. */
@AlgoliaExperimentalDsl
public fun synonym(block: DSLSynonymHit.() -> Unit): SynonymHit =
  DSLSynonymHit().apply(block).build()

private fun synonymHit(
  objectID: String,
  type: SynonymType,
  configure: DSLSynonymHit.() -> Unit,
): SynonymHit =
  DSLSynonymHit()
    .apply {
      this.objectID = objectID
      this.type = type
      configure()
    }
    .build()

/** Constructs a regular ([SynonymType.Synonym]) [SynonymHit] whose `synonyms` are [block]. */
@AlgoliaExperimentalDsl
public fun synonym(objectID: String, block: DSLStrings.() -> Unit): SynonymHit =
  synonymHit(objectID, SynonymType.Synonym) { synonyms(block) }

/** Constructs a one-way ([SynonymType.OneWaySynonym]) [SynonymHit] from [input] to [block]. */
@AlgoliaExperimentalDsl
public fun oneWaySynonym(
  objectID: String,
  input: String,
  block: DSLStrings.() -> Unit,
): SynonymHit =
  synonymHit(objectID, SynonymType.OneWaySynonym) {
    this.input = input
    synonyms(block)
  }

/** Constructs a one-typo alternative-correction ([SynonymType.AltCorrection1]) [SynonymHit]. */
@AlgoliaExperimentalDsl
public fun altCorrection1(
  objectID: String,
  word: String,
  block: DSLStrings.() -> Unit,
): SynonymHit =
  synonymHit(objectID, SynonymType.AltCorrection1) {
    this.word = word
    corrections(block)
  }

/** Constructs a two-typo alternative-correction ([SynonymType.AltCorrection2]) [SynonymHit]. */
@AlgoliaExperimentalDsl
public fun altCorrection2(
  objectID: String,
  word: String,
  block: DSLStrings.() -> Unit,
): SynonymHit =
  synonymHit(objectID, SynonymType.AltCorrection2) {
    this.word = word
    corrections(block)
  }

/** Constructs a placeholder ([SynonymType.Placeholder]) [SynonymHit]. */
@AlgoliaExperimentalDsl
public fun placeholder(
  objectID: String,
  placeholder: String,
  block: DSLStrings.() -> Unit,
): SynonymHit =
  synonymHit(objectID, SynonymType.Placeholder) {
    this.placeholder = placeholder
    replacements(block)
  }
