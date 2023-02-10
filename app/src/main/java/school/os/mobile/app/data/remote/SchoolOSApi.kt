package school.os.mobile.app.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import school.os.mobile.app.data.remote.dto.LoginUserDto
import school.os.mobile.app.data.remote.dto.UserDto
import school.os.mobile.app.data.remote.dto.UserResultDto


interface SchoolOSApi {

    @GET("users/list/")
    suspend fun getUsers(): List<UserDto>

    @GET("users/list/{userId}/")
    suspend fun getUserById(@Path("userId") userId: String): UserDto

    @POST("users/login/")
    suspend fun login(@Body user: LoginUserDto): UserResultDto

    @POST("users/check_phone_number/")
    suspend fun checkPhoneNumber(@Body user: LoginUserDto): UserResultDto

    @POST("users/reset_password/")
    suspend fun resetPassword(@Body user: LoginUserDto): UserResultDto

    @POST("users/set_password/")
    suspend fun setPassword(@Body user: LoginUserDto): UserResultDto

}