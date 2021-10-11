package com.algolia.search.util

/**
 * A [Closeable] is a source or destination of data that can be closed.
 * The close method is invoked to release resources that the object is holding.
 */
public expect interface Closeable {

    /**
     * Closes this stream and releases any system resources associated with it.
     * If the stream is already closed then invoking this method has no effect.
     **/
    public fun close()
}

/**
 * Executes the given block function on this resource and then closes it down correctly whether an exception is thrown or not.
 */
public inline fun <C : Closeable, R> C.use(block: (C) -> R): R {
    var closed = false
    return try {
        block(this)
    } catch (first: Throwable) {
        try {
            closed = true
            close()
        } catch (second: Throwable) {
            first.addSuppressedInternal(second)
        }
        throw first
    } finally {
        if (!closed) close()
    }
}

@PublishedApi
internal expect fun Throwable.addSuppressedInternal(other: Throwable)
