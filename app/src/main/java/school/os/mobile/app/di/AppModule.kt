package school.os.mobile.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import school.os.mobile.app.data.remote.SchoolOSApi
import school.os.mobile.app.data.repository.UserRepositoryImpl
import school.os.mobile.app.domain.repository.UserRepository
import school.os.mobile.app.utils.Constants
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSchoolOSAPi(): SchoolOSApi {
        return Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SchoolOSApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(schoolOSApi: SchoolOSApi): UserRepository {
        return UserRepositoryImpl(schoolOSApi)
    }
}