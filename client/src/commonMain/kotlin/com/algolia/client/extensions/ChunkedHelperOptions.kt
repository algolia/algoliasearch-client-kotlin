package com.algolia.client.extensions

/**
 * Optional configuration shared across chunked helpers that batch records and poll for task
 * completion.
 *
 * @param maxRetries The maximum number of retries when polling for task completion. Defaults to
 *   [DEFAULT_MAX_RETRIES].
 */
public data class ChunkedHelperOptions(public val maxRetries: Int = DEFAULT_MAX_RETRIES)
