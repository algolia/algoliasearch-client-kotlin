package com.algolia.search.model.internal.request

import com.algolia.search.model.dictionary.DictionaryEntry
import com.algolia.search.serialize.KeyAction
import com.algolia.search.serialize.KeyAddEntry
import com.algolia.search.serialize.KeyBody
import com.algolia.search.serialize.KeyClearExistingDictionaryEntries
import com.algolia.search.serialize.KeyDeleteEntry
import com.algolia.search.serialize.KeyRequests
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RequestDictionary(
    @SerialName(KeyClearExistingDictionaryEntries) val clearExistingDictionaryEntries: Boolean,
    @SerialName(KeyRequests) val requests: List<Request>,
) {

    constructor(
        clearExistingDictionaryEntries: Boolean,
        entries: List<DictionaryEntry<*>>,
        action: Request.Action,
    ) : this(clearExistingDictionaryEntries, entries.map { Request(action, it) })

    @Serializable
    internal data class Request(
        @SerialName(KeyAction) val action: Action,
        @SerialName(KeyBody) val body: DictionaryEntry<*>,
    ) {

        @Serializable
        internal enum class Action {
            /** Add the entry in the dictionary */
            @SerialName(KeyAddEntry)
            AddEntry,

            /** Delete the entry from the dictionary */
            @SerialName(KeyDeleteEntry)
            DeleteEntry
        }
    }
}
