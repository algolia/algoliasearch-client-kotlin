package com.algolia.client.dsl

import kotlin.reflect.KMutableProperty1

internal class Additive<B, R>(
  private val seed: (B) -> (R.() -> Unit)? = { null },
  private val wrap: R.(R.() -> Unit) -> Unit = { it() },
  private val write: B.(R.() -> Unit) -> Unit,
) {
  private val blocks: MutableList<R.() -> Unit> = mutableListOf()

  fun add(block: R.() -> Unit) {
    val wrap = wrap
    val wrapped: R.() -> Unit = { wrap(block) }
    blocks += wrapped
  }

  fun applyTo(builder: B, merge: Boolean) {
    if (blocks.isEmpty()) return
    val seeded = if (merge) seed(builder) else null
    val recorded = blocks
    builder.write {
      seeded?.invoke(this)
      for (block in recorded) block()
    }
  }
}

internal fun <B, R, T> listAdditive(
  property: KMutableProperty1<B, List<T>?>,
  append: R.(List<T>) -> Unit,
  writeList: B.(R.() -> Unit) -> Unit,
): Additive<B, R> =
  Additive(
    seed = { builder -> property.get(builder)?.let { base -> { append(base) } } },
    write = writeList,
  )

internal fun <B, R, L> filterAdditive(
  field: String,
  baseRows: (B) -> List<List<L>>?,
  addRows: R.(List<List<L>>) -> Unit,
  wrap: R.(R.() -> Unit) -> Unit,
  write: B.(R.() -> Unit) -> Unit,
): Additive<B, R> =
  Additive(
    seed = { builder ->
      val rows =
        checkNotNull(baseRows(builder)) {
          "The composer base assigns $field directly, so add { $field { } } fragments cannot " +
            "merge into it. Set it with $field { } in the base, or move it to add { }."
        }
      val seed: R.() -> Unit = { addRows(rows) }
      seed
    },
    wrap = wrap,
    write = write,
  )

internal class ComposerCore<A, B>(
  private val newAdditions: () -> A,
  private val newBuilder: () -> B,
  private val base: B.() -> Unit,
  private val merge: Boolean,
  private val applyAdditions: A.(B, Boolean) -> Unit,
) {
  private val additions: MutableList<A.() -> Unit> = mutableListOf()
  private val overrides: MutableList<B.() -> Unit> = mutableListOf()

  fun add(block: A.() -> Unit) {
    additions += block
  }

  fun override(block: B.() -> Unit) {
    overrides += block
  }

  fun build(): B {
    val collected = newAdditions()
    for (block in additions) collected.block()
    val builder = newBuilder()
    builder.base()
    collected.applyAdditions(builder, merge)
    for (block in overrides) builder.block()
    return builder
  }
}
