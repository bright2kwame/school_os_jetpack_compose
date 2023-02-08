package school.os.mobile.app.utils

sealed class DataParser<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : DataParser<T>(data)
    class Error<T>(message: String, data: T? = null) : DataParser<T>(data, message)
    class Loading<T>(data: T? = null) : DataParser<T>(data)
}