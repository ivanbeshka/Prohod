package com.example.prohod.di.modules

import com.example.prohod.utils.AuthSharedPref
import com.example.prohod.utils.INeedTokenUpdate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

//    @Singleton
//    @Provides
//    fun provideINeedTokenUpdate(authenticator: Authenticator): INeedTokenUpdate {
//        return authenticator
//    }
}