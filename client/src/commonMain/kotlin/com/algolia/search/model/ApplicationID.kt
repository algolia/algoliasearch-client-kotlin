package com.algolia.search.model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.internal.Raw

/**
 * Your [ApplicationID] (or App ID) is what Algolia uses to identify your app, where we keep all your indices.
 * Can't be blank or empty.
 */
public data class ApplicationID(override val raw: String) : Raw<String> {

    init {
        if (raw.isBlank()) throw EmptyStringException("ApplicationID")
    }

    override fun toString(): String {
        return raw
    }
}
