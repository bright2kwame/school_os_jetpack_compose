package school.os.mobile.app.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import school.os.mobile.app.data.local.dao.UserDao
import school.os.mobile.app.data.remote.SchoolOSApi
import school.os.mobile.app.data.remote.dto.*
import school.os.mobile.app.domain.model.SimpleResult
import school.os.mobile.app.domain.model.User
import school.os.mobile.app.domain.model.UserResult
import school.os.mobile.app.domain.repository.UserRepository
import school.os.mobile.app.domain.use_case.UseCaseConstants
import school.os.mobile.app.utils.DataParser
import school.os.mobile.app.utils.ResultStatusCode
import school.os.mobile.app.utils.UserPostAction
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val schoolOSApi: SchoolOSApi,
    private val userDao: UserDao,
) : UserRepository {

    override fun update(user: User): Flow<DataParser<UserResult>> = flow {
        emit(DataParser.Loading<UserResult>())
        try {
            val results = schoolOSApi.updateUser(user = user.toUserDto())
            //MARK: check if the response is a success
            if (results.responseCode == ResultStatusCode.SUCCESS.value.toString()) {
                userDao.updateUser(results.result.toUserEntity())
                emit(DataParser.Success<UserResult>(results.toUserResult()))
            } else {
                emit(DataParser.Error<UserResult>(results.responseMessage))
            }
        } catch (e: HttpException) {
            emit(
                DataParser.Error<UserResult>(
                    e.localizedMessage ?: UseCaseConstants.INTERNET_FAILED_MESSAGE
                )
            )
        } catch (e: IOException) {
            emit(DataParser.Error<UserResult>(UseCaseConstants.SOMETHING_FAILED))
        }
    }

    override fun changePassword(
        oldPassword: String,
        newPassword: String
    ): Flow<DataParser<UserResult>> = flow {
        emit(DataParser.Loading<UserResult>())
        try {
            val result = schoolOSApi.changePassword(
                ChangePasswordDto(
                    oldPassword = oldPassword,
                    newPassword = newPassword
                )
            )
            emit(DataParser.Success<UserResult>(result.toUserResult()))
        } catch (e: HttpException) {
            emit(
                DataParser.Error<UserResult>(
                    e.localizedMessage ?: UseCaseConstants.INTERNET_FAILED_MESSAGE
                )
            )
        } catch (e: IOException) {
            emit(DataParser.Error<UserResult>(UseCaseConstants.SOMETHING_FAILED))
        }
    }

    override fun resetPassword(
        username: String,
        code: String,
        password: String
    ): Flow<DataParser<SimpleResult>> = flow {
        emit(DataParser.Loading<SimpleResult>())
        try {
            val result = schoolOSApi.resetPassword(
                ResetPasswordDto(
                    phoneNumber = username,
                    uniqueCode = code,
                    password = password
                )
            )
            emit(DataParser.Success<SimpleResult>(result.toSimpleResult()))
        } catch (e: HttpException) {
            emit(
                DataParser.Error<SimpleResult>(
                    e.localizedMessage ?: UseCaseConstants.INTERNET_FAILED_MESSAGE
                )
            )
        } catch (e: IOException) {
            emit(DataParser.Error<SimpleResult>(UseCaseConstants.SOMETHING_FAILED))
        }
    }

    override fun initPasswordReset(username: String): Flow<DataParser<SimpleResult>> = flow {
        emit(DataParser.Loading<SimpleResult>())
        try {
            val result = schoolOSApi.initPasswordReset(
                InitPasswordChangeDto(
                    phoneNumber = username
                )
            )
            emit(DataParser.Success<SimpleResult>(result.toSimpleResult()))
        } catch (e: HttpException) {
            emit(
                DataParser.Error<SimpleResult>(
                    e.localizedMessage ?: UseCaseConstants.INTERNET_FAILED_MESSAGE
                )
            )
        } catch (e: IOException) {
            emit(DataParser.Error<SimpleResult>(UseCaseConstants.SOMETHING_FAILED))
        }
    }

    override fun checkByUserName(phone: String): Flow<DataParser<SimpleResult>> = flow {
        emit(DataParser.Loading<SimpleResult>())
        try {
            val user = schoolOSApi.checkUserByUserName(CheckUserDto(phone))
            println(user.toString())
            emit(DataParser.Success<SimpleResult>(user.toSimpleResult()))
        } catch (e: HttpException) {
            emit(
                DataParser.Error<SimpleResult>(
                    e.localizedMessage ?: UseCaseConstants.INTERNET_FAILED_MESSAGE
                )
            )
        } catch (e: IOException) {
            println("USE_CASE $e")
            emit(DataParser.Error<SimpleResult>(UseCaseConstants.SOMETHING_FAILED))
        }
    }

    override fun verifyPhoneNumber(
        phone: String,
        code: String,
        password: String
    ): Flow<DataParser<SimpleResult>> =
        flow {
            emit(DataParser.Loading<SimpleResult>())
            try {
                val user = schoolOSApi.verifyPhoneNumber(
                    VerifyPhoneDto(
                        phoneNumber = phone,
                        uniqueCode = code,
                        password = password
                    )
                )
                emit(DataParser.Success<SimpleResult>(user.toSimpleResult()))
            } catch (e: HttpException) {
                emit(
                    DataParser.Error<SimpleResult>(
                        e.localizedMessage ?: UseCaseConstants.INTERNET_FAILED_MESSAGE
                    )
                )
            } catch (e: IOException) {
                emit(DataParser.Error<SimpleResult>(UseCaseConstants.SOMETHING_FAILED))
            }
        }

    override fun login(phone: String, password: String): Flow<DataParser<UserResult>> = flow {
        emit(DataParser.Loading<UserResult>())
        try {
            val results = schoolOSApi.login(UserLoginDto(phone, password))
            //MARK: check if the response is a success
            if (results.responseCode == ResultStatusCode.SUCCESS.value.toString()) {
                userDao.deleteUsers()
                userDao.addUser(results.result.toUserEntity())
                emit(DataParser.Success<UserResult>(results.toUserResult()))
            } else {
                emit(DataParser.Error<UserResult>(results.responseMessage))
            }
        } catch (e: HttpException) {
            emit(
                DataParser.Error<UserResult>(
                    e.localizedMessage ?: UseCaseConstants.INTERNET_FAILED_MESSAGE
                )
            )
        } catch (e: IOException) {
            emit(DataParser.Error<UserResult>(UseCaseConstants.SOMETHING_FAILED))
        }
    }

    override fun get(id: String, postAction: UserPostAction): Flow<DataParser<UserResult>> = flow {
        emit(DataParser.Loading<UserResult>())
        try {
            val user = schoolOSApi.getUser(id)
            //MARK: if the action is to update then take action
            if (postAction == UserPostAction.UPDATE) {
                userDao.updateUser(user = user.result.toUserEntity())
            } else if (postAction == UserPostAction.SET) {
                userDao.addUser(user.result.toUserEntity())
            }
            emit(DataParser.Success<UserResult>(user.toUserResult()))
        } catch (e: HttpException) {
            emit(
                DataParser.Error<UserResult>(
                    e.localizedMessage ?: UseCaseConstants.INTERNET_FAILED_MESSAGE
                )
            )
        } catch (e: IOException) {
            emit(DataParser.Error<UserResult>(UseCaseConstants.SOMETHING_FAILED))
        }
    }

}