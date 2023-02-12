package school.os.mobile.app.di

import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor(private val headerToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain) : Response {
        val request = chain.request().newBuilder()
            .header("Authorisation", "Bearer $headerToken")
            .build()
        return chain.proceed(request)
    }
}