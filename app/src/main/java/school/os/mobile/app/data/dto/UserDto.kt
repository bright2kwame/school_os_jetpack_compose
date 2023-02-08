package school.os.mobile.app.data.dto

import com.google.gson.annotations.SerializedName
import school.os.mobile.app.domain.model.User

data class UserDto(
    @SerializedName("first_name")
    val firstName: String,
    val id: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("phone_number")
    val phoneNumber: String
)

//MARK: convert userDto to user
fun UserDto.toUser(): User{
   return User(id = id, firstName = firstName, lastName = lastName, phoneNumber = phoneNumber)
}