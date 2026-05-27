package com.algolia.client.configuration

/**
 * Configuration options for the ingestion transporter used by the `*WithTransformation` helpers on
 * [com.algolia.client.api.SearchClient].
 *
 * When installed on a `SearchClient` via [com.algolia.client.api.SearchClient.withTransformation]
 * or [com.algolia.client.api.SearchClient.setTransformationOptions], an
 * [com.algolia.client.api.IngestionClient] is eagerly built. Pass a [ClientOptions] to override the
 * Ingestion API defaults (25 s connect/read/write timeouts, region-derived hosts, no compression);
 * only the fields explicitly set there replace the defaults. The parent search [ClientOptions] is
 * never forwarded to the ingestion transporter.
 *
 * @param region Algolia region for the Ingestion API (e.g. `"us"` or `"eu"`). Required.
 * @param clientOptions Optional [ClientOptions] forwarded to the ingestion transporter.
 */
public data class TransformationOptions(
  public val region: String,
  public val clientOptions: ClientOptions? = null,
) {
  init {
    require(region.isNotBlank()) {
      "`region` is required in `transformationOptions`. See https://www.algolia.com/doc/libraries/sdk/methods/ingestion"
    }
  }
}
