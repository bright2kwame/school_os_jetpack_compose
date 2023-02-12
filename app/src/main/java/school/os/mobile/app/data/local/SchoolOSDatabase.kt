package school.os.mobile.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import school.os.mobile.app.data.local.dao.UserDao
import school.os.mobile.app.data.local.entity.UserEntity


@Database(
    entities = [UserEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class SchoolOSDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}