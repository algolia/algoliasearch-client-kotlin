import kotlin.test.*


internal infix fun <T> T.shouldEqual(expected: T) {
    assertEquals(expected, this)
}

internal infix fun <T> T.shouldNotEqual(expected: T) {
    assertNotEquals(expected, this)
}

internal fun Any?.shouldBeNull() {
    assertNull(this)
}

internal fun Any?.shouldNotBeNull() {
    assertNotNull(this)
}

internal fun Boolean.shouldBeTrue() {
    assertTrue(this)
}

internal fun Boolean.shouldBeFalse() {
    assertFalse(this)
}

internal infix fun <T> Collection<T>?.shouldContain(element: T) {
    (this?.contains(element) ?: false).shouldBeTrue()
}

internal infix fun <T> Collection<T>?.shouldNotContain(element: T) {
    (this?.contains(element) ?: false).shouldBeFalse()
}

internal fun <T> Collection<T>.shouldBeEmpty() {
    this.isEmpty().shouldBeTrue()
}

internal fun <T> Collection<T>.shouldNotBeEmpty() {
    this.isNotEmpty().shouldBeTrue()
}

internal infix fun <K, V> Map<K, V>?.shouldContainKey(key: K) {
    (this?.containsKey(key) ?: false).shouldBeTrue()
}

internal infix fun <K, V> Map<K, V>?.shouldNotContainKey(key: K) {
    (this?.containsKey(key) ?: false).shouldBeFalse()
}

internal infix fun <K, V> Map<K, V>?.shouldContainValue(value: V) {
    (this?.containsValue(value) ?: false).shouldBeTrue()
}

internal infix fun <K, V> Map<K, V>?.shouldNotContainValue(value: V) {
    (this?.containsValue(value) ?: false).shouldBeFalse()
}

internal fun <K, V> Map<K, V>.shouldBeEmpty() {
    this.isEmpty().shouldBeTrue()
}

internal fun <K, V> Map<K, V>.shouldNotBeEmpty() {
    this.isNotEmpty().shouldBeTrue()
}

internal inline fun <reified T : Throwable> shouldFailWith(noinline block: suspend () -> Unit): T {
    return assertFailsWith(T::class, null) {
        runBlocking {
            block()
        }
    }
}