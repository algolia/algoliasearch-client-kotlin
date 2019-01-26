package com.algolia.search.model

import com.algolia.search.exception.EmptyStringException


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