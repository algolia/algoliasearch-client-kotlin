package com.algolia.client.extensions

import com.algolia.client.api.IngestionClient
import com.algolia.client.exception.AlgoliaApiException
import com.algolia.client.exception.AlgoliaClientException
import com.algolia.client.extensions.internal.retryUntil
import com.algolia.client.model.ingestion.Action
import com.algolia.client.model.ingestion.Event
import com.algolia.client.model.ingestion.PushTaskPayload
import com.algolia.client.model.ingestion.PushTaskRecords
import com.algolia.client.model.ingestion.WatchResponse
import com.algolia.client.transport.RequestOptions
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * Helper: Chunks the given `objects` list into requests of at most `batchSize` records and pushes
 * each chunk through the Ingestion API's transformation pipeline using [IngestionClient.push].
 *
 * If [waitForTasks] is true, the helper polls the Ingestion API after every `max(1, batchSize /
 * 10)` pushes for the corresponding events, providing backpressure on long jobs.
 *
 * @param indexName The index in which to perform the request.
 * @param objects The list of objects to push.
 * @param action The action to perform on the objects.
 * @param waitForTasks Whether to wait for the server to finish processing each push.
 * @param batchSize The size of the chunk. Default is 1000.
 * @param referenceIndexName Required when targeting an index that does not have a push connector
 *   setup, but you wish to attach another index's transformation to it.
 * @param requestOptions Additional request configuration.
 * @param chunkedOptions Optional shared configuration for chunked helpers (e.g. `maxRetries`).
 * @return The list of responses from each push request.
 */
public suspend fun IngestionClient.chunkedPush(
  indexName: String,
  objects: List<JsonObject>,
  action: Action = Action.AddObject,
  waitForTasks: Boolean,
  batchSize: Int = 1000,
  referenceIndexName: String? = null,
  requestOptions: RequestOptions? = null,
  chunkedOptions: ChunkedHelperOptions = ChunkedHelperOptions(),
): List<WatchResponse> {
  require(batchSize > 0) { "`batchSize` must be greater than 0" }

  val maxRetries = chunkedOptions.maxRetries
  val responses = mutableListOf<WatchResponse>()
  val pollInterval = maxOf(1, batchSize / 10)

  objects.chunked(batchSize).chunked(pollInterval).forEach { superBatch ->
    val pushed = superBatch.map { chunk ->
      push(
        indexName = indexName,
        pushTaskPayload =
          PushTaskPayload(action = action, records = chunk.map { it.toPushTaskRecord() }),
        watch = false,
        referenceIndexName = referenceIndexName,
        requestOptions = requestOptions,
      )
    }
    responses += pushed

    if (waitForTasks) {
      pushed.forEach { pollEvent(it, maxRetries, requestOptions) }
    }
  }

  return responses
}

private fun JsonObject.toPushTaskRecord(): PushTaskRecords {
  val objectID =
    this["objectID"]?.jsonPrimitive?.content
      ?: throw AlgoliaClientException(
        "each object must have an `objectID` key in order to be indexed"
      )

  return PushTaskRecords(
    objectID = objectID,
    additionalProperties = this.filterKeys { it != "objectID" },
  )
}

private suspend fun IngestionClient.pollEvent(
  response: WatchResponse,
  maxRetries: Int,
  requestOptions: RequestOptions?,
): Event {
  val eventID =
    response.eventID
      ?: throw AlgoliaClientException(
        "received unexpected response from the push endpoint, eventID must not be undefined"
      )
  return retryUntil(
      retry = {
        try {
          Result.success(getEvent(response.runID, eventID, requestOptions))
        } catch (e: AlgoliaApiException) {
          if (e.httpErrorCode == 404) Result.failure(e) else throw e
        }
      },
      until = { it.isSuccess },
      maxRetries = maxRetries,
      timeout = Duration.INFINITE,
      initialDelay = 1500.milliseconds,
      maxDelay = 5.seconds,
    )
    .getOrThrow()
}
