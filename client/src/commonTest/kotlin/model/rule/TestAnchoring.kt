package model.rule

import com.algolia.search.model.rule.Anchoring.Contains
import com.algolia.search.model.rule.Anchoring.EndsWith
import com.algolia.search.model.rule.Anchoring.Is
import com.algolia.search.model.rule.Anchoring.Other
import com.algolia.search.model.rule.Anchoring.StartsWith
import com.algolia.search.serialize.internal.Key
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestAnchoring {

    @Test
    fun raw() {
        Is.raw shouldEqual Key.Is
        StartsWith.raw shouldEqual Key.StartsWith
        EndsWith.raw shouldEqual Key.EndsWith
        Contains.raw shouldEqual Key.Contains
        Other(unknown).raw shouldEqual unknown
    }
}
