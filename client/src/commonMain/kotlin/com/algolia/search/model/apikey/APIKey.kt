package com.algolia.search.model.apikey

import com.algolia.search.client.ClientSearch
import com.algolia.search.model.APIKey

public fun APIKey.getSecuredApiKeyRemainingValidity(): Long {
    return ClientSearch.getSecuredApiKeyRemainingValidity(this)
}

public fun APIKey.generateSecuredAPIKey(restriction: SecuredAPIKeyRestriction): APIKey {
    return ClientSearch.generateAPIKey(this, restriction)
}
