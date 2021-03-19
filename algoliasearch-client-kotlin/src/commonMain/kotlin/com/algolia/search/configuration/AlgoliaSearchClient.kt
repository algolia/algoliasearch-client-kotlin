package com.algolia.search.configuration

import com.algolia.search.internal.BuildConfig

/**
 * Singleton exposing information on the library.
 */
public object AlgoliaSearchClient {

    /**
     * Current version of the library.
     */
    public const val version: String = BuildConfig.version
}
