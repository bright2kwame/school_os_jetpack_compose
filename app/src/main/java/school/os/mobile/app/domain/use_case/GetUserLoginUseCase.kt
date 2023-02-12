package school.os.mobile.app.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import school.os.mobile.app.domain.model.UserResult
import school.os.mobile.app.domain.repository.UserRepository
import school.os.mobile.app.utils.DataParser

class GetUserLoginUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(phone: String, password: String): Flow<DataParser<UserResult>> {
        if (phone.isBlank() || password.isBlank()) {
            return flow { }
        }
        return repository.login(phone, password)
    }
}