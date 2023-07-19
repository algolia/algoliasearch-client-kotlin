package com.algolia.search.util

public actual interface Closeable {
    public actual fun close()
}

@PublishedApi
internal actual fun Throwable.addSuppressedInternal(other: Throwable) {
    // no-op.
}
