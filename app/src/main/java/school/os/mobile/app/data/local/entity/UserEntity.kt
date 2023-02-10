package school.os.mobile.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import school.os.mobile.app.domain.model.User


@Entity
data class UserEntity(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    @PrimaryKey val id: String
) {
    //MARK: convert entity to model user
    fun toUser(): User {
        return User(firstName = firstName, lastName = lastName, phoneNumber = phoneNumber, id = id)
    }
}
