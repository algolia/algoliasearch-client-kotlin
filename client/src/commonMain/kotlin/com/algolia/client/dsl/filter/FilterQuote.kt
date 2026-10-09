package com.algolia.client.dsl.filter

internal object FilterQuote {
  private val BARE: Regex = Regex("[A-Za-z0-9_.\\-]+")
  private val KEYWORD: Regex = Regex("(?i)\\b(?:AND|OR|NOT|TO)\\b")

  fun quote(raw: String): String {
    if (!needsQuotes(raw)) return raw
    return "\"${raw.replace("\\", "\\\\").replace("\"", "\\\"")}\""
  }

  fun needsQuotes(raw: String): Boolean = !BARE.matches(raw) || KEYWORD.containsMatchIn(raw)
}
