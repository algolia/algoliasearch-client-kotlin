package com.algolia.search.serialize.internal

import com.algolia.search.serialize.KeyAsc
import com.algolia.search.serialize.KeyDesc
import com.algolia.search.serialize.KeyEqualOnly
import com.algolia.search.serialize.KeyFilterOnly
import com.algolia.search.serialize.KeyOrdered
import com.algolia.search.serialize.KeySearchable
import com.algolia.search.serialize.KeyUnordered

internal val regexAsc = Regex("^$KeyAsc\\((.*)\\)$")
internal val regexDesc = Regex("^$KeyDesc\\((.*)\\)$")
internal val regexEqualOnly = Regex("^$KeyEqualOnly\\((.*)\\)$")
internal val regexSnippet = Regex("^(.*):(\\d+)$")
internal val regexOrdered = Regex("^$KeyOrdered\\((.*)\\)$")
internal val regexUnordered = Regex("^$KeyUnordered\\((.*)\\)$")
internal val regexFilterOnly = Regex("^$KeyFilterOnly\\((.*)\\)$")
internal val regexSearchable = Regex("^$KeySearchable\\((.*)\\)$")
internal val regexFacet = Regex("^\\{facet:(.*)\\}$")
internal val regexPlaceholder = Regex("^<(.*)>$")
internal val regexPoint = Regex("^(.*),(.*)$")
internal val regexUserToken = Regex("^[a-zA-Z0-9_\\-\\.\\:]*\$") // alpha-numeric and/or IP address (IPv4/IPv6)
