package com.algolia.search.transport.internal

import com.algolia.search.model.filter.internal.Converter

internal expect object Gzip : Converter<String, ByteArray>
