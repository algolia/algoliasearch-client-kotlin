package com.algolia.search.model.dictionary

import kotlinx.serialization.Serializable

@Serializable
public class DictionarySettings(
    public val disableStandardEntries: Map<Dictionary, Map<String, Boolean>>,
)
