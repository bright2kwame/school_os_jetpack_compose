package school.os.mobile.app.domain.repository

import school.os.mobile.app.data.dto.UserDto

interface UserRepository {

    suspend fun getUsers(): List<UserDto>

    suspend fun getUserById(userId: String): UserDto
}