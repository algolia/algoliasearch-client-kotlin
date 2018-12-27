package client.data


data class Point(
    val latitude: Float,
    val longitude: Float
) {

    internal val floats = listOf(latitude, longitude)
}