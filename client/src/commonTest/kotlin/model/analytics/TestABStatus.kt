package model.analytics

import com.algolia.search.model.analytics.ABTestStatus.Active
import com.algolia.search.model.analytics.ABTestStatus.Expired
import com.algolia.search.model.analytics.ABTestStatus.Failed
import com.algolia.search.model.analytics.ABTestStatus.Other
import com.algolia.search.model.analytics.ABTestStatus.Stopped
import com.algolia.search.serialize.internal.KeyActive
import com.algolia.search.serialize.internal.KeyExpired
import com.algolia.search.serialize.internal.KeyFailed
import com.algolia.search.serialize.internal.KeyStopped
import unknown
import kotlin.test.Test

internal class TestABStatus {

    @Test
    fun raw() {
        Expired.raw to KeyExpired
        Stopped.raw to KeyStopped
        Active.raw to KeyActive
        Failed.raw to KeyFailed
        Other(unknown).raw to unknown
    }
}
