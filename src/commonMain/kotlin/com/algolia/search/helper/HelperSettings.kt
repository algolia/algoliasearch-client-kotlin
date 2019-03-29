package com.algolia.search.helper

import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.Settings

@DslMarker
annotation class HelperSettings

public fun settingsBuilder(init: Settings.() -> Unit) = Settings().apply(init)

public fun Settings.setAttributesToRetrieve(vararg attributes: Attribute, excludeAttributes: Boolean = false) {
    attributesToRetrieve = buildAttributesToRetrieve(*attributes, excludeAttributes = excludeAttributes)
}

public fun Settings.setAllAttributesToRetrieve() {
    attributesToRetrieve = listOf(all)
}
