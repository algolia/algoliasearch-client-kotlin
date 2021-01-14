package com.algolia.search.model.dictionary

import com.algolia.search.model.ObjectID
import com.algolia.search.model.search.Language
import com.algolia.search.serialize.KeyDecomposition
import com.algolia.search.serialize.KeyDisable
import com.algolia.search.serialize.KeyLanguage
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.serialize.KeyWord
import com.algolia.search.serialize.KeyWords
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class DictionaryEntry(
    /**
     * Unique identifier of the entry to add or override.
     */
    @SerialName(KeyObjectID) val objectID: ObjectID,
    /**
     * Language ISO code supported by the dictionary.
     */
    @SerialName(KeyLanguage) val language: Language,
    /**
     * The stop word being added or modified.
     * When word already exists in the standard dictionary provided by Algolia,
     * the entry can be overridden by the one provided by the user.
     */
    @SerialName(KeyWord) val word: String? = null,
    /**
     * List of word declensions.
     * The entry overrides existing entries when any of these words are defined
     * in the standard dictionary provided by Algolia.
     */
    @SerialName(KeyWords) val words: List<String>? = null,
    /**
     * When empty, the key word is considered as a compound atom.
     * Otherwise, it is the decomposition of word.
     */
    @SerialName(KeyDecomposition) val decomposition: List<String>? = null,
    /**
     * TODO: missing documentation?
     */
    @SerialName(KeyDisable) val disable: Boolean? = null,
)
