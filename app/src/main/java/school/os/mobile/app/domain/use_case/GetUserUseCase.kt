package school.os.mobile.app.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import school.os.mobile.app.data.remote.dto.toUser
import school.os.mobile.app.domain.model.User
import school.os.mobile.app.domain.repository.UserRepository
import school.os.mobile.app.utils.DataParser
import java.io.IOException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(userId: String): Flow<DataParser<User>> = flow {
        try {
            emit(DataParser.Loading<User>())
            val user = repository.getUserById(userId).toUser()
            emit(DataParser.Success<User>(user))
        } catch (e: HttpException) {
            emit(
                DataParser.Error<User>(
                    e.localizedMessage ?: "")
                )
        } catch (e: IOException) {
            emit(DataParser.Error<User>(""))
        }
    }
}