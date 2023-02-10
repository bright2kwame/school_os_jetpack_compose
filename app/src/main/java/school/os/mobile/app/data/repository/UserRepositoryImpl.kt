package school.os.mobile.app.data.repository

import school.os.mobile.app.data.remote.SchoolOSApi
import school.os.mobile.app.data.remote.dto.UserDto
import school.os.mobile.app.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
   private val schoolOSApi: SchoolOSApi
): UserRepository {

    override suspend fun getUsers(): List<UserDto> {
       return schoolOSApi.getUsers()
    }

    override suspend fun getUserById(userId: String): UserDto {
        return schoolOSApi.getUserById(userId)
    }
}