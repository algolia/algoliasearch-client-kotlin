package client.data


data class BoundingBox(val p1: Float, val p2: Float, val p3: Float, val p4: Float) {

    internal val floats = listOf(p1, p2, p3, p4)
}