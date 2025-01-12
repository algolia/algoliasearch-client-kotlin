/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.ingestion

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * SourceBigQuery
 *
 * @param projectID Project ID of the BigQuery source.
 * @param datasetID Dataset ID of the BigQuery source.
 * @param dataType
 * @param table Table name for the BigQuery export.
 * @param tablePrefix Table prefix for a Google Analytics 4 data export to BigQuery.
 * @param customSQLRequest Custom SQL request to extract data from the BigQuery table.
 * @param uniqueIDColumn Name of a column that contains a unique ID which will be used as `objectID` in Algolia.
 */
@Serializable
public data class SourceBigQuery(

  /** Project ID of the BigQuery source. */
  @SerialName(value = "projectID") val projectID: String,

  /** Dataset ID of the BigQuery source. */
  @SerialName(value = "datasetID") val datasetID: String,

  @SerialName(value = "dataType") val dataType: BigQueryDataType? = null,

  /** Table name for the BigQuery export. */
  @SerialName(value = "table") val table: String? = null,

  /** Table prefix for a Google Analytics 4 data export to BigQuery. */
  @SerialName(value = "tablePrefix") val tablePrefix: String? = null,

  /** Custom SQL request to extract data from the BigQuery table. */
  @SerialName(value = "customSQLRequest") val customSQLRequest: String? = null,

  /** Name of a column that contains a unique ID which will be used as `objectID` in Algolia. */
  @SerialName(value = "uniqueIDColumn") val uniqueIDColumn: String? = null,
) : SourceInput,
  SourceUpdateInput
