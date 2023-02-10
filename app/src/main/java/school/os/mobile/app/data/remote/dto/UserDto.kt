package school.os.mobile.app.data.remote.dto

import com.google.gson.annotations.SerializedName
import school.os.mobile.app.domain.model.User

data class UserDto(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("phone_number")
    val phoneNumber: String
)

data class UserResultDto(
    @SerializedName("response_code")
    val responseCode: String,
    @SerializedName("user")
    val result: UserDto
)

//MARK: convert userDto to user
fun UserDto.toUser(): User {
    return User(id = id, firstName = firstName, lastName = lastName, phoneNumber = phoneNumber)
}

//MARK: login user request
data class LoginUserDto(
    @SerializedName("phone_number")
    val phoneNumber: String
)
