package com.algolia.client.exception

/**
 * Algolia runtime exception.
 *
 * @param message the detail message
 * @param cause the cause of the exception
 */
public sealed class AlgoliaRuntimeException(message: String? = null, cause: Throwable? = null) :
  RuntimeException(message, cause)

/**
 * Exception thrown when an error occurs during API requests.
 *
 * @param message the detail message
 * @param cause the cause of the exception
 */
public class AlgoliaClientException(message: String? = null, cause: Throwable? = null) :
  AlgoliaRuntimeException(message, cause)

/**
 * Exception thrown in case of API failure.
 *
 * @param message the detail message
 * @param cause the cause of the exception
 * @param httpErrorCode
 * @param correlationId the `Correlation-ID` response header of the failed request, when present
 */
public class AlgoliaApiException(
  message: String? = null,
  cause: Throwable? = null,
  public val httpErrorCode: Int? = null,
  public val correlationId: String? = null,
) : AlgoliaRuntimeException(message, cause)

/**
 * Exception thrown when all hosts are unreachable. When several errors occurred, use the last one
 * as the cause for the returned exception.
 *
 * @param exceptions list of thrown exceptions
 * @param correlationId the `Correlation-ID` of the last attempt that returned one, when present
 */
public class AlgoliaRetryException(
  public val exceptions: List<Throwable>,
  public val correlationId: String? = null,
) :
  AlgoliaRuntimeException(
    "Error(s) while processing the retry strategy. If the error persists, please visit our help center https://alg.li/support-unreachable-hosts or reach out to the Algolia Support team: https://alg.li/support",
    exceptions.last(),
  )

/**
 * Exception thrown when an error occurs during the wait strategy. For example: maximum number of
 * retry exceeded.
 *
 * @param message the detail message
 */
public class AlgoliaWaitException(message: String? = null) : AlgoliaRuntimeException(message)

/**
 * Exception thrown when an error occurs during an iterable helper execution.
 *
 * @param message the detail message
 */
public class AlgoliaIterableException(message: String? = null) : AlgoliaRuntimeException(message)
