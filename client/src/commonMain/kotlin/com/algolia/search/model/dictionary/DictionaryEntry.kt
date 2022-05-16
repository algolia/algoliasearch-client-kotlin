package com.algolia.search.model.dictionary

import com.algolia.search.model.ObjectID
import com.algolia.search.model.dictionary.DictionaryEntry.State.Disabled
import com.algolia.search.model.dictionary.DictionaryEntry.State.Enabled
import com.algolia.search.model.search.Language
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents an entry in a given dictionary.
 */
public sealed class DictionaryEntry {

    /**
     * Unique identifier of the entry to add or override.
     */
    public abstract val objectID: ObjectID

    /**
     * Language ISO code supported by the dictionary.
     */
    public abstract val language: Language

    @Serializable
    public data class Stopword(
        @SerialName(Key.ObjectID) override val objectID: ObjectID,
        @SerialName(Key.Language) override val language: Language,
        /**
         * The stop word being added or modified.
         * When word already exists in the standard dictionary provided by Algolia,
         * the entry can be overridden by the one provided by the user.
         */
        @SerialName(Key.Word) public val word: String,
        /**
         * The state of the entry.
         */
        @SerialName(Key.State) public val state: State? = Enabled,
    ) : DictionaryEntry()

    @Serializable
    public data class Plural(
        @SerialName(Key.ObjectID) override val objectID: ObjectID,
        @SerialName(Key.Language) override val language: Language,
        /**
         * List of word declensions.
         * The entry overrides existing entries when any of these words are defined
         * in the standard dictionary provided by Algolia.
         */
        @SerialName(Key.Words) public val words: List<String>,
    ) : DictionaryEntry()

    @Serializable
    public data class Compound(
        @SerialName(Key.ObjectID) override val objectID: ObjectID,
        @SerialName(Key.Language) override val language: Language,

        /**
         * The stop word being added or modified.
         * When word already exists in the standard dictionary provided by Algolia,
         * the entry can be overridden by the one provided by the user.
         */
        @SerialName(Key.Word) public val word: String,
        /**
         * When empty, the key word is considered as a compound atom.
         * Otherwise, it is the decomposition of word.
         */
        @SerialName(Key.Decomposition) public val decomposition: List<String>,
    ) : DictionaryEntry()

    /**
     * The state of the entry:
     * - [Enabled]: the entry is enabled.
     * - [Disabled]: the entry is disabled.
     */
    @Serializable
    public enum class State {
        @SerialName(Key.Enabled)
        Enabled,

        @SerialName(Key.Disabled)
        Disabled
    }
}
