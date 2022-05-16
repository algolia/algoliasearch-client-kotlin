package com.algolia.search.model.internal.request

import com.algolia.search.model.internal.request.RequestDictionary.Request.Action.AddEntry
import com.algolia.search.model.internal.request.RequestDictionary.Request.Action.DeleteEntry
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement

/**
 * Batch request structure for dictionary entries.
 */
internal sealed class RequestDictionary {

    /**
     * When `true`, start the batch by removing all the custom entries from the dictionary.
     *
     * Engine default: `false`
     */
    abstract val clearExistingDictionaryEntries: Boolean

    /**
     * List of operations to batch.
     */
    abstract val requests: List<Request>

    @Serializable
    internal data class Add(
        @SerialName(Key.ClearExistingDictionaryEntries) override val clearExistingDictionaryEntries: Boolean,
        @SerialName(Key.Requests) override val requests: List<Request>,
    ) : RequestDictionary() {

        constructor(
            entries: JsonArray,
            clearExistingDictionaryEntries: Boolean,
        ) : this(clearExistingDictionaryEntries, entries.map { Request(it, AddEntry) })
    }

    @Serializable
    internal data class Delete(
        @SerialName(Key.ClearExistingDictionaryEntries) override val clearExistingDictionaryEntries: Boolean,
        @SerialName(Key.Requests) override val requests: List<Request>,
    ) : RequestDictionary() {

        constructor(
            entries: JsonArray,
            clearExistingDictionaryEntries: Boolean,
        ) : this(clearExistingDictionaryEntries, entries.map { Request(it, DeleteEntry) })
    }

    @Serializable
    internal data class Request(
        val body: JsonElement,
        val action: Action,
    ) {

        @Serializable
        internal enum class Action {
            /** Add the entry in the dictionary */
            @SerialName(Key.AddEntry)
            AddEntry,

            /** Delete the entry from the dictionary */
            @SerialName(Key.DeleteEntry)
            DeleteEntry
        }
    }
}
