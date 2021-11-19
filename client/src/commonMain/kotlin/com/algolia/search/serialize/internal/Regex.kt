package com.algolia.search.serialize.internal

import com.algolia.search.serialize.KeyAsc
import com.algolia.search.serialize.KeyDesc
import com.algolia.search.serialize.KeyEqualOnly
import com.algolia.search.serialize.KeyFilterOnly
import com.algolia.search.serialize.KeyOrdered
import com.algolia.search.serialize.KeySearchable
import com.algolia.search.serialize.KeyUnordered
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable internal val regexAsc = Regex("^$KeyAsc\\((.*)\\)$")
@SharedImmutable internal val regexDesc = Regex("^$KeyDesc\\((.*)\\)$")
@SharedImmutable internal val regexEqualOnly = Regex("^$KeyEqualOnly\\((.*)\\)$")
@SharedImmutable internal val regexSnippet = Regex("^(.*):(\\d+)$")
@SharedImmutable internal val regexOrdered = Regex("^$KeyOrdered\\((.*)\\)$")
@SharedImmutable internal val regexUnordered = Regex("^$KeyUnordered\\((.*)\\)$")
@SharedImmutable internal val regexFilterOnly = Regex("^$KeyFilterOnly\\((.*)\\)$")
@SharedImmutable internal val regexSearchable = Regex("^$KeySearchable\\((.*)\\)$")
@SharedImmutable internal val regexFacet = Regex("^\\{facet:(.*)\\}$")
@SharedImmutable internal val regexPlaceholder = Regex("^<(.*)>$")
@SharedImmutable internal val regexPoint = Regex("^(.*),(.*)$")
@SharedImmutable internal val regexUserToken = Regex("^[a-zA-Z0-9_\\-.:]*\$") // alpha-numeric and/or IP address (IPv4/IPv6)
