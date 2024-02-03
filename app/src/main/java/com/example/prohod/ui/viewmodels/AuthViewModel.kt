package com.example.prohod.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prohod.data.repo.MainRepository
import com.example.prohod.di.modules.NeedTokenUpdateImpl
import com.example.prohod.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val needTokenUpdate: NeedTokenUpdateImpl,
    private val repo: MainRepository
) : ViewModel() {
    val isTokenUpdating = MutableLiveData(Resource.success(false))

    fun subscribeOnTokenUpdating() {
        viewModelScope.launch {
            repo.loginExistedUser()
            needTokenUpdate.getNeedTokenUpdate()
                .collect {
                    if (it) {
                        isTokenUpdating.value = Resource.success(true)
                        val isLogin = repo.loginExistedUser()
                        if (isLogin) {
                            isTokenUpdating.value = Resource.success(false)
                        } else {
                            isTokenUpdating.value = Resource.error("updateTokenError", false)
                        }
                    }
                }
        }

    }
}