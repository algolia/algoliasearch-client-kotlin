package com.algolia.search.saas.data

import com.algolia.search.saas.exception.EmptyStringException


data class ApplicationID(override val raw: String) : Raw<String> {

    init {
        if (raw.isBlank()) {
            throw EmptyStringException("ApplicationID")
        }
    }

    override fun toString(): String {
        return raw
    }
}