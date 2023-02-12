package school.os.mobile.app.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import school.os.mobile.app.domain.model.SimpleResult
import school.os.mobile.app.domain.repository.UserRepository
import school.os.mobile.app.utils.DataParser
import java.io.IOException
import javax.inject.Inject

class GetVerifyPhoneUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(
        phone: String,
        code: String,
        password: String
    ): Flow<DataParser<SimpleResult>> {
        if (phone.isBlank() || code.isBlank()) {
            return flow {
                emit(DataParser.Error<SimpleResult>("Provide a valid phone number and verification code"))
            }
        }
        return repository.verifyPhoneNumber(phone, code, password)
    }
}