package school.os.mobile.app.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import school.os.mobile.app.domain.model.User
import school.os.mobile.app.domain.model.UserResult
import school.os.mobile.app.domain.repository.UserRepository
import school.os.mobile.app.utils.DataParser
import school.os.mobile.app.utils.UserPostAction
import java.io.IOException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(userId: String, postAction: UserPostAction): Flow<DataParser<UserResult>> {
        if (userId.isBlank()) {
            return flow { }
        }
        return repository.get(userId, postAction)
    }
}