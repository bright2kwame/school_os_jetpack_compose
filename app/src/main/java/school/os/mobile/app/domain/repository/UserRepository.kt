package school.os.mobile.app.domain.repository

import kotlinx.coroutines.flow.Flow
import school.os.mobile.app.data.remote.dto.SimpleResultDto
import school.os.mobile.app.domain.model.SimpleResult
import school.os.mobile.app.domain.model.User
import school.os.mobile.app.domain.model.UserResult
import school.os.mobile.app.utils.DataParser
import school.os.mobile.app.utils.UserPostAction


interface UserRepository {

    fun checkByUserName(phone: String): Flow<DataParser<SimpleResult>>
    fun verifyPhoneNumber(
        phone: String,
        code: String,
        password: String
    ): Flow<DataParser<SimpleResult>>

    fun login(phone: String, password: String): Flow<DataParser<UserResult>>
    fun get(id: String, postAction: UserPostAction): Flow<DataParser<UserResult>>
    fun update(user: User): Flow<DataParser<UserResult>>
    fun changePassword(oldPassword: String, newPassword: String): Flow<DataParser<UserResult>>
    fun resetPassword(
        username: String,
        code: String,
        password: String
    ): Flow<DataParser<SimpleResult>>

    fun initPasswordReset(username: String): Flow<DataParser<SimpleResult>>
}