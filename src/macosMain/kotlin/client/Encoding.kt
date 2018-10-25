package client


actual fun String.encodeUTF8(): String {
    return this.toUtf8OrThrow().stringFromUtf8OrThrow()
}