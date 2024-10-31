package com.algolia.client.extensions.internal

import com.algolia.client.exception.AlgoliaIterableException
import kotlinx.coroutines.delay
import kotlin.time.Duration

public data class IterableError<T>(
  public val validate: (T) -> Boolean,
  public val message: ((T) -> String)? = null
)

public suspend fun <T> createIterable(
  execute: suspend (T?) -> T,
  validate: (T) -> Boolean,
  aggregator: ((T) -> Unit)? = null,
  timeout: () -> Duration = { Duration.ZERO },
  error: IterableError<T>? = null
): T {
  suspend fun executor(previousResponse: T? = null): T {
    val response = execute(previousResponse)

    if (aggregator != null) {
      aggregator(response)
    }

    if (validate(response)) {
      return response
    }

    if (error != null && error.validate(response)) {
      val message = error.message?.invoke(response) ?: "An error occurred"
      throw AlgoliaIterableException(message)
    }

    delay(timeout().inWholeMilliseconds)

    return executor(response)
  }

  return executor()
}
