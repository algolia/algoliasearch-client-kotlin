package model.rule

import com.algolia.search.model.rule.Anchoring.*
import com.algolia.search.serialize.KeyContains
import com.algolia.search.serialize.KeyEndsWith
import com.algolia.search.serialize.KeyIs
import com.algolia.search.serialize.KeyStartsWith
import shouldEqual
import unknown
import kotlin.test.Test


internal class TestAnchoring {

    @Test
    fun raw() {
        Is.raw shouldEqual KeyIs
        StartsWith.raw shouldEqual KeyStartsWith
        EndsWith.raw shouldEqual KeyEndsWith
        Contains.raw shouldEqual KeyContains
        Other(unknown).raw shouldEqual unknown
    }
}