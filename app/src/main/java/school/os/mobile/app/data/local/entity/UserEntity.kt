package school.os.mobile.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import school.os.mobile.app.domain.model.User
import java.util.Date


@Entity(tableName = "users")
data class UserEntity(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val userName: String,
    val email: String,
    val type: String,
    val createdDate: Date,
    val authToken: String,
    @PrimaryKey val id: String
) {
    //MARK: convert entity to model user
    fun toUser(): User {
        return User(
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber,
            id = id,
            email = email,
            type = type,
            username = userName,
            dateCreated = createdDate.time.toString(),
            authToken = authToken
        )
    }
}
