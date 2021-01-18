package com.algolia.search.model.dictionary

import com.algolia.search.model.search.Language
import com.algolia.search.serialize.KeyStopwords
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public class DictionarySettings(
    public val disableStandardEntries: DisableStandardEntries? = null,
)

/**
 * Map of language ISO code supported by the dictionary (e.g., “en” for English) to a boolean value.
 * When set to true, the standard entries for the language are disabled.
 * Changes are set for the given languages only. To re-enable standard entries, set the language to false.
 * To reset settings to default values, set dictionary to `null`.
 */
@Serializable
public data class DisableStandardEntries(
    @SerialName(KeyStopwords) val stopwords: Map<Language, Boolean>?,
)
