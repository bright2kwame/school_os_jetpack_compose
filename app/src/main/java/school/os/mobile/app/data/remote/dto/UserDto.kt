package school.os.mobile.app.data.remote.dto

import com.google.gson.annotations.SerializedName
import school.os.mobile.app.data.local.entity.UserEntity
import school.os.mobile.app.domain.model.SimpleResult
import school.os.mobile.app.domain.model.User
import school.os.mobile.app.domain.model.UserResult
import school.os.mobile.app.utils.DateUtil
import java.util.*

/**
 * set the params of the object sent for password change
 */
data class ChangePasswordDto(
    @SerializedName("old_password")
    val oldPassword: String,
    @SerializedName("new_password")
    val newPassword: String
)

/**
 * reset password data objects
 */
data class ResetPasswordDto(
    @SerializedName("unique_code")
    val uniqueCode: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("password")
    val password: String
)

/*
 * data objects to initialise the password rest process
 */
data class InitPasswordChangeDto(
    @SerializedName("username")
    val phoneNumber: String,
)

/**
 * data object to check the existence of the username
 */
data class CheckUserDto(
    @SerializedName("username")
    val phoneNumber: String
)

/**
 * the data object to verify the phone number
 */
data class VerifyPhoneDto(
    @SerializedName("username")
    val phoneNumber: String,
    @SerializedName("unique_code")
    val uniqueCode: String,
    @SerializedName("password")
    val password: String
)

data class UserLoginDto(
    @SerializedName("username")
    val phoneNumber: String,
    @SerializedName("password")
    val password: String
)

data class SimpleResultDto(
    @SerializedName("response_code")
    val responseCode: String,
    @SerializedName("message")
    val responseMessage: String?
) {
    fun toSimpleResult(): SimpleResult {
        return SimpleResult(responseCode = responseCode, responseMessage = responseMessage ?: "")
    }

    override fun toString(): String {
        return "{response_code : $responseCode, response_message: $responseMessage}"
    }
}

data class UserResultDto(
    @SerializedName("response_code")
    val responseCode: String,
    @SerializedName("message")
    val responseMessage: String,
    @SerializedName("results")
    val result: UserDto

) {
    fun toUserResult(): UserResult {
        return UserResult(
            responseCode = responseCode,
            responseMessage = responseMessage,
            result = result.toUser()
        )
    }
}

data class UserDto(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("user_type")
    val type: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("auth_token")
    val token: String
) {
    fun toUser(): User {
        return User(
            id = id, firstName = firstName,
            username = username, dateCreated = createdAt, type = type, email = email,
            lastName = lastName, phoneNumber = phoneNumber, authToken = token
        )
    }

    fun toUserEntity(): UserEntity {
        return UserEntity(
            id = id,
            firstName = firstName,
            userName = username,
            createdDate = DateUtil.fromStringToDate(createdAt) ?: Date(),
            type = type,
            email = email,
            lastName = lastName,
            phoneNumber = phoneNumber,
            authToken = token
        )
    }

}
