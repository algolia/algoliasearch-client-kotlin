package client


actual object Time {

    actual fun getCurrentTimeMillis(): Long {
        return System.currentTimeMillis()
    }
}