package school.os.mobile.app.utils

import java.text.SimpleDateFormat
import java.util.*

sealed class DataParser<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : DataParser<T>(data)
    class Error<T>(message: String, data: T? = null) : DataParser<T>(data, message)
    class Loading<T>(data: T? = null) : DataParser<T>(data)
}


enum class ResultStatusCode(val value: Int) {
    SUCCESS(100),
    NOT_FOUND(101),
    NOT_VERIFIED(200);

    companion object {
        fun fromInt(value: Int) = ResultStatusCode.values().first { it.value == value }
    }
}

enum class UserPostAction {
    UPDATE,
    SET,
    NONE,
}

object DateUtil{
    fun fromStringToDate(value: String): Date? {
        return SimpleDateFormat(Constants.API_DATE_FORMAT, Locale.ROOT).parse(value)
    }
}