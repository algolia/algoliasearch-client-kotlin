package plugin

import Dependency

object Android : Dependency {
    override val group = "com.android.tools.build"
    override val artifact = "gradle"
    override val version = "4.0.1"
}
