package client.data


data class Polygon(
    val point1: Point,
    val point2: Point,
    val point3: Point,
    private val points: List<Point>
) {

    constructor(point1: Point, point2: Point, point3: Point, vararg points: Point) : this(
        point1,
        point2,
        point3,
        points.toList()
    )

    operator fun get(index: Int): Point {
        return when (index) {
            0 -> point1
            1 -> point2
            2 -> point3
            else -> points[index - 3]
        }
    }

    internal val floats = listOf(
        *point1.floats.toTypedArray(),
        *point2.floats.toTypedArray(),
        *point3.floats.toTypedArray(),
        *points.flatMap { it.floats }.toTypedArray()
    )
}