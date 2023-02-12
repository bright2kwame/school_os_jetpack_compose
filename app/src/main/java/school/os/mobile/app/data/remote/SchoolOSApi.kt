package school.os.mobile.app.data.remote

import retrofit2.http.*
import school.os.mobile.app.data.remote.dto.*


interface SchoolOSApi {

    @POST("users/check_username/")
    suspend fun checkUserByUserName(@Body checkUserDto: CheckUserDto): SimpleResultDto

    @POST("users/verify_phone_number/")
    suspend fun verifyPhoneNumber(@Body checkUserDto: VerifyPhoneDto): SimpleResultDto

    @GET("users/list/")
    suspend fun getUsers(): List<UserResultDto>

    @GET("users/{userId}/")
    suspend fun getUser(@Path("userId") userId: String): UserResultDto

    @PUT("users/me/")
    suspend fun updateUser(@Body user: UserDto): UserResultDto

    @POST("users/login/")
    suspend fun login(@Body userLoginDto: UserLoginDto): UserResultDto

    @POST("users/password_reset_sms/")
    suspend fun initPasswordReset(@Body user: InitPasswordChangeDto): SimpleResultDto

    @POST("users/reset_password/")
    suspend fun resetPassword(@Body user: ResetPasswordDto): SimpleResultDto

    @POST("users/change_password/")
    suspend fun changePassword(
        @Body user: ChangePasswordDto
    ): UserResultDto

}