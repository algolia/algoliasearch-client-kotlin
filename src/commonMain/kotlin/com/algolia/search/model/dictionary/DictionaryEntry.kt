package com.algolia.search.model.dictionary

import com.algolia.search.model.ObjectID
import com.algolia.search.model.dictionary.Dictionary.Compounds
import com.algolia.search.model.dictionary.Dictionary.Plurals
import com.algolia.search.model.dictionary.Dictionary.Stopwords
import com.algolia.search.model.search.Language
import com.algolia.search.serialize.KeyDecomposition
import com.algolia.search.serialize.KeyDisabled
import com.algolia.search.serialize.KeyEnabled
import com.algolia.search.serialize.KeyLanguage
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.serialize.KeyState
import com.algolia.search.serialize.KeyWord
import com.algolia.search.serialize.KeyWords
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Encoder

@Serializable(DictionaryEntry.Companion::class)
public sealed class DictionaryEntry<T : Dictionary> {

    /**
     * Unique identifier of the entry to add or override.
     */
    public abstract val objectID: ObjectID

    /**
     * Language ISO code supported by the dictionary.
     */
    public abstract val language: Language

    @Serializable
    public class Stopword(
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
    ) : DictionaryEntry<Stopwords>()

    @Serializable
    public class Plural(
        @SerialName(KeyObjectID) override val objectID: ObjectID,
        @SerialName(KeyLanguage) override val language: Language,
        /**
         * List of word declensions.
         * The entry overrides existing entries when any of these words are defined
         * in the standard dictionary provided by Algolia.
         */
        @SerialName(KeyWords) public val words: List<String>,
    ) : DictionaryEntry<Plurals>()

    @Serializable
    public class Compound(
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
    ) : DictionaryEntry<Compounds>()

    @Serializable
    public enum class State {
        @SerialName(KeyEnabled)
        Enabled,

        @SerialName(KeyDisabled)
        Disabled
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(DictionaryEntry::class)
    public companion object : KSerializer<DictionaryEntry<*>> {

        override fun serialize(encoder: Encoder, value: DictionaryEntry<*>) {
            when (value) {
                is Stopword -> encoder.encodeSerializableValue(Stopword.serializer(), value)
                is Plural -> encoder.encodeSerializableValue(Plural.serializer(), value)
                is Compound -> encoder.encodeSerializableValue(Compound.serializer(), value)
            }
        }
    }
}
