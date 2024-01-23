package com.example.prohod.utils

import com.example.prohod.data.repo.MainRepository
import javax.inject.Inject

//class Authenticator @Inject constructor(
//    private val authSharedPref: AuthSharedPref,
//    private val authRepo: MainRepository
//) : INeedTokenUpdate {
//    private val needTokenUpdate = PublishSubject.create<Boolean>()
//
//    suspend fun login(): Boolean {
//
//    }
//
//    suspend fun register(): Boolean {
//        if (isRegistered()) {
//            return true
//        }
//
//        return authRepo.login()
//    }
//
//    suspend fun authOldUser(): Boolean {
//        if (!isRegistered()) { //maybe need this
//            return register()
//        }
//
//    }
//
//    fun getNeedTokenUpdate(): Observable<Boolean> {
//        return needTokenUpdate
//    }
//
//    override fun updateToken() {
//        needTokenUpdate.onNext(true)
//    }
//
//    private fun isRegistered(): Boolean {
//        return authSharedPref.token != null
//    }
//}