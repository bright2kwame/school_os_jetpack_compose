package school.os.mobile.app.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import school.os.mobile.app.data.local.entity.UserEntity


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUsers(users: List<UserEntity>)

    @Query("DELETE FROM users")
    suspend fun deleteUsers()

    @Delete
    suspend fun delete(userEntity: UserEntity)

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUser(userId: String)

    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<UserEntity>

    @Query("SELECT * from users LIMIT 1")
    suspend fun getUser(): UserEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(note: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("SELECT * FROM users ORDER BY createdDate DESC")
    fun getUsersOrdered(): List<UserEntity>
}