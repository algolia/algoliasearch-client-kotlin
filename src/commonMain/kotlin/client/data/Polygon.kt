package client.data


class Polygon(
    val point1: Point,
    val point2: Point,
    val point3: Point,
    private vararg val points: Point
) {

    operator fun get(index: Int): Point {
        return when (index) {
            0 -> point1
            1 -> point2
            2 -> point3
            else -> points[index - 3]
        }
    }

    override fun hashCode(): Int {
        return point1.hashCode() + point2.hashCode() + point3.hashCode() + points.toList().hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Polygon) other.hashCode() == hashCode() else false
    }

    internal val floats = listOf(
        *point1.floats.toTypedArray(),
        *point2.floats.toTypedArray(),
        *point3.floats.toTypedArray(),
        *points.flatMap { it.floats }.toTypedArray()
    )
}