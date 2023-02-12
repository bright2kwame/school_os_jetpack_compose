package school.os.mobile.app.domain.model

import school.os.mobile.app.data.remote.dto.UserDto


data class SimpleResult(
    val responseCode: String,
    val responseMessage: String
)

data class UserResult(
    val responseCode: String,
    val responseMessage: String,
    val result: User
)

data class User(
    val firstName: String,
    val id: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val type: String,
    val username: String,
    val dateCreated: String,
    val authToken: String
) {
    fun toUserDto(): UserDto {
        return UserDto(
            firstName,
            id,
            lastName,
            phoneNumber,
            email,
            username,
            type,
            dateCreated,
            token = authToken
        )
    }
}