package com.example.prohod.di.modules

import com.example.prohod.utils.INeedTokenUpdate
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.runBlocking

class NeedTokenUpdateImpl : INeedTokenUpdate {
    private val needTokenUpdate = MutableSharedFlow<Boolean>()

    fun getNeedTokenUpdate(): SharedFlow<Boolean> {
        return needTokenUpdate.asSharedFlow()
    }

    override fun updateToken() {
        runBlocking {
            needTokenUpdate.emit(true)
        }
    }
}