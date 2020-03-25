package com.algolia.search.model

import com.algolia.search.exception.EmptyStringException

/**
 * Your [ApplicationID] (or App ID) is what Algolia uses to identify your app, where we keep all your indices.
 * Can't be blank or empty.
 */
data class ApplicationID(override val raw: String) : Raw<String> {

    init {
        if (raw.isBlank()) throw EmptyStringException("ApplicationID")
    }

    override fun toString(): String {
        return raw
    }
}
