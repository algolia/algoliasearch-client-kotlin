package model.analytics

import com.algolia.search.model.analytics.ABTestStatus.Active
import com.algolia.search.model.analytics.ABTestStatus.Expired
import com.algolia.search.model.analytics.ABTestStatus.Failed
import com.algolia.search.model.analytics.ABTestStatus.Other
import com.algolia.search.model.analytics.ABTestStatus.Stopped
import com.algolia.search.serialize.internal.Key
import unknown
import kotlin.test.Test

internal class TestABStatus {

    @Test
    fun raw() {
        Expired.raw to Key.Expired
        Stopped.raw to Key.Stopped
        Active.raw to Key.Active
        Failed.raw to Key.Failed
        Other(unknown).raw to unknown
    }
}
