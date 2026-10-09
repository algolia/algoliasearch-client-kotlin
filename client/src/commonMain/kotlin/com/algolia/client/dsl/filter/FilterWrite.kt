package com.algolia.client.dsl.filter

internal class FilterWrite<V : Any, L : Filter>(val value: V?, val rows: List<List<L>>)

internal fun <V : Any, L : Filter> rowsBehind(
  current: V?,
  write: FilterWrite<V, L>?,
): List<List<L>>? =
  when {
    current == null -> emptyList()
    write != null && current == write.value -> write.rows
    else -> null
  }
