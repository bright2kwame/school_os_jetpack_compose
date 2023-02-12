package school.os.mobile.app.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import bright.mobile.worddefinition.data.util.JsonParser
import com.google.gson.reflect.TypeToken
import school.os.mobile.app.utils.Constants
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromMeaningsJson(json: String): List<String> {
        return jsonParser.fromJson<ArrayList<String>>(
            json,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningsJson(meanings: List<String>): String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromStringToDate(value: String?): Date? {
        value?.let {
            return SimpleDateFormat(Constants.API_DATE_FORMAT, Locale.ROOT).parse(value)
        }
        return Date()
    }

    @TypeConverter
    fun dateToString(date: Date?): String {
        return date?.toString() ?: ""
    }
}
