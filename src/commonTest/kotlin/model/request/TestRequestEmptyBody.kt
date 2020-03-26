package model.request

import com.algolia.search.model.request.EmptyBody
import kotlin.test.Test
import shouldEqual

internal class TestRequestEmptyBody {

    @Test
    fun test() {
        EmptyBody shouldEqual ""
    }
}
