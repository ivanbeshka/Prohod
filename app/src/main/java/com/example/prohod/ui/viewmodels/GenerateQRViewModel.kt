package com.example.prohod.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prohod.data.repo.MainRepository
import com.example.prohod.utils.AuthSharedPref
import com.example.prohod.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateQRViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val authSharedPref: AuthSharedPref
) : ViewModel() {
    val isRequestAccepted = MutableLiveData<Resource<Boolean>>(Resource.success(null))
    val fullName = authSharedPref.login

    fun updateIsRequestAccepted() {
        isRequestAccepted.value = Resource.loading(null)
        viewModelScope.launch {
            isRequestAccepted.value = Resource.success(mainRepository.isVisitRequestAccepted())
        }
    }
}