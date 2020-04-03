package model.request

import com.algolia.search.model.request.EmptyBody
import shouldEqual
import kotlin.test.Test

internal class TestRequestEmptyBody {

    @Test
    fun test() {
        EmptyBody shouldEqual ""
    }
}
