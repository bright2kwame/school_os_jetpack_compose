package school.os.mobile.app.data

import retrofit2.http.GET
import retrofit2.http.Path
import school.os.mobile.app.data.dto.UserDto

interface SchoolOSApi {

    @GET("users/list/")
    suspend fun getUsers(): List<UserDto>

    @GET("users/list/{userId}/")
    suspend fun getUserById(@Path("userId") userId: String): UserDto

}