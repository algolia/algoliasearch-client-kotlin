package snippets

import clientLatency
import indexName

internal val client get() = clientLatency
internal val index get() = client.initIndex(indexName)