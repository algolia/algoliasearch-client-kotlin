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