package school.os.mobile.app.di

import android.app.Application
import androidx.room.Room
import bright.mobile.worddefinition.data.util.GsonParser
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import school.os.mobile.app.BuildConfig
import school.os.mobile.app.data.local.Converters
import school.os.mobile.app.data.local.SchoolOSDatabase
import school.os.mobile.app.data.remote.SchoolOSApi
import school.os.mobile.app.data.repository.UserRepositoryImpl
import school.os.mobile.app.domain.repository.UserRepository
import school.os.mobile.app.domain.use_case.GetCheckPhoneNumberUseCase
import school.os.mobile.app.domain.use_case.GetPasswordUseCase
import school.os.mobile.app.domain.use_case.GetUserLoginUseCase
import school.os.mobile.app.domain.use_case.GetVerifyPhoneUseCase
import school.os.mobile.app.utils.Constants
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSchoolOSAPi(): SchoolOSApi {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        val httpClient = OkHttpClient().newBuilder()
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor(CustomInterceptor(""))

        return Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(SchoolOSApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(schoolOSApi: SchoolOSApi, db: SchoolOSDatabase): UserRepository {
        return UserRepositoryImpl(schoolOSApi, db.userDao)
    }

    @Provides
    @Singleton
    fun provideCheckUseCase(repository: UserRepository): GetCheckPhoneNumberUseCase {
        return GetCheckPhoneNumberUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideVerifyPhoneNumberUseCase(repository: UserRepository): GetVerifyPhoneUseCase {
        return GetVerifyPhoneUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(repository: UserRepository): GetUserLoginUseCase {
        return GetUserLoginUseCase(repository)
    }

    @Provides
    @Singleton
    fun providePasswordUseCase(repository: UserRepository): GetPasswordUseCase {
        return GetPasswordUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSchoolOSDatabase(app: Application): SchoolOSDatabase {
        return Room.databaseBuilder(
            app, SchoolOSDatabase::class.java, "school_os_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }
}