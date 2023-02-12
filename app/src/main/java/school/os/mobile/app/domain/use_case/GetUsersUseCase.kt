package school.os.mobile.app.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import school.os.mobile.app.domain.model.User
import school.os.mobile.app.domain.repository.UserRepository
import school.os.mobile.app.utils.DataParser
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<DataParser<List<User>>> {
         return flow {  }
    }
}