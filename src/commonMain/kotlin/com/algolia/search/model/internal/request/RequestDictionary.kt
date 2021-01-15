package com.algolia.search.model.internal.request

import com.algolia.search.model.ObjectID
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

@Serializable
internal data class RequestAddDictionary(
    @SerialName(KeyClearExistingDictionaryEntries) val clearExistingDictionaryEntries: Boolean,
    @SerialName(KeyRequests) val requests: List<Request.AddEntry>,
) {

    constructor(
        entries: List<DictionaryEntry<*>>,
        clearExistingDictionaryEntries: Boolean,
    ) : this(clearExistingDictionaryEntries, entries.map { Request.AddEntry(it) })
}

@Serializable
internal data class RequestDeleteDictionary(
    @SerialName(KeyClearExistingDictionaryEntries) val clearExistingDictionaryEntries: Boolean,
    @SerialName(KeyRequests) val requests: List<Request.DeleteEntry>,
) {

    constructor(
        entries: List<ObjectID>,
        clearExistingDictionaryEntries: Boolean,
    ) : this(clearExistingDictionaryEntries, entries.map { Request.DeleteEntry(DeleteObject(it)) })
}

internal sealed class Request {

    abstract val action: Action

    @Serializable
    data class AddEntry(@SerialName(KeyBody) val body: DictionaryEntry<*>) : Request() {
        @SerialName(KeyAction)
        override val action: Action = Action.AddEntry
    }

    @Serializable
    data class DeleteEntry(@SerialName(KeyBody) val body: DeleteObject) : Request() {
        @SerialName(KeyAction)
        override val action: Action = Action.DeleteEntry
    }
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
