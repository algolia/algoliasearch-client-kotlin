package com.algolia.client

import com.algolia.client.extensions.internal.encodeKeySHA256
import kotlin.test.Test
import kotlin.test.assertEquals

class TestHashing {

  @Test
  fun sha256() {
    val expected = encodeKeySHA256(key = "1234", message = "test")
    val actual = "24c4f0295e1bea74f9a5cb5bc40525c8889d11c78c4255808be00defe666671f"
    assertEquals(expected, actual)
  }
}
