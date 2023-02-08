package school.os.mobile.app.domain.use_case

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import school.os.mobile.app.R
import school.os.mobile.app.data.dto.toUser
import school.os.mobile.app.domain.model.User
import school.os.mobile.app.domain.repository.UserRepository
import school.os.mobile.app.utils.DataParser
import java.io.IOException
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository,
    private val context: Context
) {
    operator fun invoke(): Flow<DataParser<List<User>>> = flow {
        try {
            emit(DataParser.Loading<List<User>>())
            val users = repository.getUsers().map { it.toUser() }
            emit(DataParser.Success<List<User>>(users))
        } catch (e: HttpException) {
            emit(
                DataParser.Error<List<User>>(
                    e.localizedMessage ?: context.getString(R.string.unknown_error)
                )
            )
        } catch (e: IOException) {
            emit(DataParser.Error<List<User>>(context.getString(R.string.no_internet_message)))
        }
    }
}