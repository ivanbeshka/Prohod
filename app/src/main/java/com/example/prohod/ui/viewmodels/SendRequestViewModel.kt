package com.example.prohod.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prohod.data.repo.MainRepository
import com.example.prohod.ui.screens.ChapterTextField
import com.example.prohod.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendRequestViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    val isSendRequest = MutableLiveData(Resource.success(false))

    val fieldToValue = mutableMapOf(
        ChapterTextField.NAME to MutableLiveData(""),
        ChapterTextField.SURNAME to MutableLiveData(""),
        ChapterTextField.PATRONYMIC to MutableLiveData(""),
        ChapterTextField.PASSPORT_NUMBER_AND_SERIES to MutableLiveData(""),
        ChapterTextField.PASSPORT_ISSUE_DATE to MutableLiveData(""),
        ChapterTextField.PASSPORT_ISSUED_BY to MutableLiveData(""),
        ChapterTextField.VISIT_DATE to MutableLiveData(""),
        ChapterTextField.WHO_TO_VISIT to MutableLiveData(""),
        ChapterTextField.VISIT_REASON to MutableLiveData(""),
        ChapterTextField.EMAIL to MutableLiveData("")
    )

    fun sendRequest() {
        viewModelScope.launch {
            val name = fieldToValue[ChapterTextField.NAME]?.value ?: ""
            val surname = fieldToValue[ChapterTextField.SURNAME]?.value ?: ""
            val patronymic = fieldToValue[ChapterTextField.PATRONYMIC]?.value ?: ""
            val passportNumberAndSeries = fieldToValue[ChapterTextField.PASSPORT_NUMBER_AND_SERIES]?.value ?: ""
            val passportIssueDate = "2024-02-02T10:43:24.573Z" //fieldToValue[ChapterTextField.PASSPORT_ISSUE_DATE]?.value ?:
            val passportIssuedBy = fieldToValue[ChapterTextField.PASSPORT_ISSUED_BY]?.value ?: ""
            val visitDate = "2024-02-02T10:43:24.573Z" //fieldToValue[ChapterTextField.VISIT_DATE]?.value ?:
            val whoToVisit = "05fd16d6-d17a-40a7-9c63-aa81e1ccc266" //fieldToValue[ChapterTextField.WHO_TO_VISIT]?.value?.data ?: ""
            val visitReason = fieldToValue[ChapterTextField.VISIT_REASON]?.value ?: ""
            val email = fieldToValue[ChapterTextField.EMAIL]?.value ?: ""

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