package com.example.prohod.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prohod.data.repo.MainRepository
import com.example.prohod.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendRequestViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    val isSendRequest = MutableLiveData(Resource.success(false))

    fun sendRequest(
        name: String,
        surname: String,
        patronymic: String,
        passportNumberAndSeries: String,
        passportIssueDate: String,
        passportIssuedBy: String,
        visitDate: String,
        whoToVisit: String,
        visitReason: String,
        email: String
    ) {
        viewModelScope.launch {
            val isComplete = mainRepository.sendVisitRequest(
                name,
                surname,
                patronymic,
                passportNumberAndSeries,
                passportIssueDate,
                passportIssuedBy,
                visitDate,
                whoToVisit,
                visitReason,
                email
            )
            isSendRequest.value = Resource.success(isComplete)
        }
    }
}