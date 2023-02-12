package school.os.mobile.app.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import school.os.mobile.app.domain.model.SimpleResult
import school.os.mobile.app.domain.model.UserResult
import school.os.mobile.app.domain.repository.UserRepository
import school.os.mobile.app.utils.DataParser

class GetPasswordUseCase(
    private val repository: UserRepository
) {
    fun changePassword(oldPassword: String, newPassword: String): Flow<DataParser<UserResult>> {
        if (oldPassword.isBlank() || newPassword.isBlank() || oldPassword != newPassword) {
            return flow {
                emit(DataParser.Error<UserResult>("Passwords must match"))
            }
        }
        return repository.changePassword(oldPassword, newPassword)
    }

    fun resetPassword(
        phone: String,
        code: String,
        password: String
    ): Flow<DataParser<SimpleResult>> {
        if (phone.isBlank() || password.isBlank()) {
            return flow {
                emit(DataParser.Error<SimpleResult>("Provide phone number, unique code and password"))
            }
        }
        return repository.resetPassword(phone, code, password)
    }

    fun initPasswordReset(phone: String): Flow<DataParser<SimpleResult>> {
        if (phone.isBlank()) {
            return flow {
                emit(DataParser.Error<SimpleResult>("Provide a valid phone number"))
            }
        }
        return repository.initPasswordReset(phone)
    }
}