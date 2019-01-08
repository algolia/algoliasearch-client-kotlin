package com.algolia.search.saas.data


data class ApplicationID(override val raw: String) : Raw<String> {

    override fun toString(): String {
        return raw
    }
}