package com.algolia.search.serialize.internal

internal val regexAsc = Regex("^${Key.Asc}\\((.*)\\)$")
internal val regexDesc = Regex("^${Key.Desc}\\((.*)\\)$")
internal val regexEqualOnly = Regex("^${Key.EqualOnly}\\((.*)\\)$")
internal val regexSnippet = Regex("^(.*):(\\d+)$")
internal val regexOrdered = Regex("^${Key.Ordered}\\((.*)\\)$")
internal val regexUnordered = Regex("^${Key.Unordered}\\((.*)\\)$")
internal val regexFilterOnly = Regex("^${Key.FilterOnly}\\((.*)\\)$")
internal val regexSearchable = Regex("^${Key.Searchable}\\((.*)\\)$")
internal val regexFacet = Regex("^\\{facet:(.*)\\}$")
internal val regexPlaceholder = Regex("^<(.*)>$")
internal val regexPoint = Regex("^(.*),(.*)$")
internal val regexUserToken = Regex("^[a-zA-Z0-9_\\-\\.\\:]*\$") // alpha-numeric and/or IP address (IPv4/IPv6)
