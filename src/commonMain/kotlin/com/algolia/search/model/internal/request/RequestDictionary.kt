package com.algolia.search.model.internal.request

import com.algolia.search.model.ObjectID
import com.algolia.search.model.dictionary.Dictionary
import com.algolia.search.model.dictionary.DictionaryEntry
import com.algolia.search.serialize.KeyAction
import com.algolia.search.serialize.KeyAddEntry
import com.algolia.search.serialize.KeyBody
import com.algolia.search.serialize.KeyClearExistingDictionaryEntries
import com.algolia.search.serialize.KeyDeleteEntry
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.serialize.KeyRequests
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    internal data class Add<T : Dictionary>(
        @SerialName(KeyClearExistingDictionaryEntries) override val clearExistingDictionaryEntries: Boolean,
        @SerialName(KeyRequests) override val requests: List<Request.Add<T>>,
    ) : RequestDictionary() {

        constructor(
            entries: List<DictionaryEntry<T>>,
            clearExistingDictionaryEntries: Boolean,
        ) : this(clearExistingDictionaryEntries, entries.map { Request.Add(it) })
    }

    @Serializable
    internal data class Delete(
        @SerialName(KeyClearExistingDictionaryEntries) override val clearExistingDictionaryEntries: Boolean,
        @SerialName(KeyRequests) override val requests: List<Request.Delete>,
    ) : RequestDictionary() {

        constructor(
            entries: List<ObjectID>,
            clearExistingDictionaryEntries: Boolean,
        ) : this(clearExistingDictionaryEntries, entries.map { Request.Delete(DeleteObject(it)) })
    }
}

/**
 * Request actions to perform
 */
@Serializable
internal sealed class Request(
    @SerialName(KeyAction) val action: Action,
) {

    @Serializable
    data class Add<T : Dictionary>(
        @SerialName(KeyBody) val body: DictionaryEntry<T>,
    ) : Request(Action.AddEntry)

    @Serializable
    data class Delete(
        @SerialName(KeyBody) val body: DeleteObject,
    ) : Request(Action.DeleteEntry)
}

@Serializable
internal data class DeleteObject(@SerialName(KeyObjectID) val objectID: ObjectID)

@Serializable
internal enum class Action {
    /** Add the entry in the dictionary */
    @SerialName(KeyAddEntry)
    AddEntry,

    /** Delete the entry from the dictionary */
    @SerialName(KeyDeleteEntry)
    DeleteEntry
}
