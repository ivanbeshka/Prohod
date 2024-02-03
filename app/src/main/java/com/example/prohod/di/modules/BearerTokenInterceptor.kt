package com.example.prohod.di.modules

import com.example.prohod.utils.AuthSharedPref
import com.example.prohod.utils.INeedTokenUpdate
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection
import javax.inject.Inject

class BearerTokenInterceptor @Inject constructor(
    private val authSharedPref: AuthSharedPref,
    private val needTokenUpdate: dagger.Lazy<INeedTokenUpdate>
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        authSharedPref.token?.let {
            request.addHeader("Authorization", "Bearer $it")
        }

        val buildRequest = request.build()
        val response = chain.proceed(buildRequest)

        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED || authSharedPref.token == null) {
            needTokenUpdate.get().updateToken()
        }

        return response
    }
}