package school.os.mobile.app.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import school.os.mobile.app.domain.model.SimpleResult
import school.os.mobile.app.domain.model.UserResult
import school.os.mobile.app.domain.repository.UserRepository
import school.os.mobile.app.utils.DataParser
import java.io.IOException
import javax.inject.Inject

class GetCheckPhoneNumberUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(phone: String): Flow<DataParser<SimpleResult>> {
        if (phone.isBlank()) {
            return flow {
                emit(DataParser.Error<SimpleResult>("Provide a valid phone number"))
            }
        }
        return repository.checkByUserName(phone)
    }
}