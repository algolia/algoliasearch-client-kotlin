package com.algolia.client

import com.algolia.client.api.SearchClient
import com.algolia.client.extensions.SecuredAPIKeyRestrictions
import com.algolia.client.extensions.generateSecuredApiKey
import com.algolia.client.extensions.securedApiKeyRemainingValidity
import com.algolia.client.model.search.SearchParamsObject
import kotlinx.datetime.Clock
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.days

class TestSecureApiKey {

  @Test
  fun securedApiKey() {
    val parentAPIKey = "SearchOnlyApiKeyKeptPrivate"
    val restriction = SecuredAPIKeyRestrictions(
      query = SearchParamsObject(filters = "_tags:user_42"),
      validUntil = Clock.System.now() + 2.days,
    )

    val client = SearchClient("appId", "apiKey")
    val securedApiKey = client.generateSecuredApiKey(parentAPIKey, restriction)
    val validity = securedApiKeyRemainingValidity(securedApiKey)
    assertTrue { validity > 1.days }
  }
}
