package com.algolia.search.model.dictionary

import com.algolia.search.model.ObjectID
import com.algolia.search.model.dictionary.Dictionary.Compounds
import com.algolia.search.model.dictionary.Dictionary.Plurals
import com.algolia.search.model.dictionary.Dictionary.Stopwords
import com.algolia.search.model.dictionary.DictionaryEntry.State.Disabled
import com.algolia.search.model.dictionary.DictionaryEntry.State.Enabled
import com.algolia.search.model.dictionary.DictionaryEntry.Type.Custom
import com.algolia.search.model.dictionary.DictionaryEntry.Type.Standard
import com.algolia.search.model.search.Language
import com.algolia.search.serialize.KSerializerDictionaryEntry
import com.algolia.search.serialize.KeyCustom
import com.algolia.search.serialize.KeyDecomposition
import com.algolia.search.serialize.KeyDisabled
import com.algolia.search.serialize.KeyEnabled
import com.algolia.search.serialize.KeyLanguage
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.serialize.KeyStandard
import com.algolia.search.serialize.KeyState
import com.algolia.search.serialize.KeyType
import com.algolia.search.serialize.KeyWord
import com.algolia.search.serialize.KeyWords
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable(KSerializerDictionaryEntry::class)
public sealed class DictionaryEntry<T : Dictionary> {

    /**
     * Unique identifier of the entry to add or override.
     */
    public abstract val objectID: ObjectID?

    /**
     * Language ISO code supported by the dictionary.
     */
    public abstract val language: Language?

    /**
     * Dictionary entry type.
     */
    public abstract val type: Type

    @Serializable
    public data class Stopword(
        @SerialName(KeyObjectID) override val objectID: ObjectID,
        @SerialName(KeyLanguage) override val language: Language,
        /**
         * The stop word being added or modified.
         * When word already exists in the standard dictionary provided by Algolia,
         * the entry can be overridden by the one provided by the user.
         */
        @SerialName(KeyWord) public val word: String,
        /**
         * The state of the entry.
         */
        @SerialName(KeyState) public val state: State? = State.Enabled,
    ) : DictionaryEntry<Stopwords>() {

        @SerialName(KeyType)
        override val type: Type = Custom
    }

    @Serializable
    public data class Plural(
        @SerialName(KeyObjectID) override val objectID: ObjectID,
        @SerialName(KeyLanguage) override val language: Language,
        /**
         * List of word declensions.
         * The entry overrides existing entries when any of these words are defined
         * in the standard dictionary provided by Algolia.
         */
        @SerialName(KeyWords) public val words: List<String>,
    ) : DictionaryEntry<Plurals>() {

        @SerialName(KeyType)
        override val type: Type = Custom
    }

    @Serializable
    public data class Compound(
        @SerialName(KeyObjectID) override val objectID: ObjectID,
        @SerialName(KeyLanguage) override val language: Language,

        /**
         * The stop word being added or modified.
         * When word already exists in the standard dictionary provided by Algolia,
         * the entry can be overridden by the one provided by the user.
         */
        @SerialName(KeyWord) public val word: String,
        /**
         * When empty, the key word is considered as a compound atom.
         * Otherwise, it is the decomposition of word.
         */
        @SerialName(KeyDecomposition) public val decomposition: List<String>,
    ) : DictionaryEntry<Compounds>() {

        @Transient
        @SerialName(KeyType)
        override val type: Type = Custom
    }

    /**
     * The state of the entry:
     * - [Enabled]: the entry is enabled.
     * - [Disabled]: the entry is disabled.
     */
    @Serializable
    public enum class State {
        @SerialName(KeyEnabled)
        Enabled,

        @SerialName(KeyDisabled)
        Disabled
    }

    /**
     * The type of the entry:
     * - [Standard]: default entry.
     * - [Custom]: custom user entry.
     */
    @Serializable
    public enum class Type {
        @SerialName(KeyStandard)
        Standard,

        @SerialName(KeyCustom)
        Custom
    }
}
