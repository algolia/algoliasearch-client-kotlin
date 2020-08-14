package model.request

import com.algolia.search.model.internal.request.EmptyBody
import shouldEqual
import kotlin.test.Test

internal class TestRequestEmptyBody {

    @Test
    fun test() {
        EmptyBody shouldEqual ""
    }
}
