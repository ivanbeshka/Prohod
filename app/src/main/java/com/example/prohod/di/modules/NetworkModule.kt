package com.example.prohod.di.modules

import com.example.prohod.data.api.ApiHelper
import com.example.prohod.data.api.ApiHelperImpl
import com.example.prohod.data.api.ApiService
import com.example.prohod.utils.AuthSharedPref
import com.example.prohod.utils.INeedTokenUpdate
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideClient(
        logging: HttpLoggingInterceptor,
        bearerTokenInterceptor: BearerTokenInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(bearerTokenInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideNeedTokenUpdateImpl(): NeedTokenUpdateImpl = NeedTokenUpdateImpl()

    @Singleton
    @Provides
    fun provideINeedTokenUpdate(needTokenUpdateImpl: NeedTokenUpdateImpl): INeedTokenUpdate = needTokenUpdateImpl

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideRetrofit(jsonConfig: Json, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(jsonConfig.asConverterFactory(CONVERTER_FACTORY_TYPE.toMediaType()))
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideJsonConfig(): Json {
        return Json { ignoreUnknownKeys = true }
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiService: ApiService): ApiHelper = ApiHelperImpl(apiService)

    private companion object {
        private const val BASE_URL = "http://51.250.94.4/api/v1/"
        private const val CONVERTER_FACTORY_TYPE = "application/json"
    }
}