import kotlin.test.*


internal infix fun <T> T.shouldEqual(actual: T) {
    assertEquals(this, actual)
}

internal infix fun <T> T.shouldNotEqual(actual: T) {
    assertNotEquals(this, actual)
}

internal fun Any?.shouldBeNull() {
    assertNull(this)
}

internal fun Boolean.shouldBeTrue() {
    assertTrue(this)
}

internal fun Boolean.shouldBeFalse() {
    assertFalse(this)
}