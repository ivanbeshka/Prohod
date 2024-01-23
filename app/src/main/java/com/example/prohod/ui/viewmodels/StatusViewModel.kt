package com.example.prohod.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.prohod.utils.Resource
import com.example.prohod.utils.StateSharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatusViewModel @Inject constructor(
    private val stateSharedPref: StateSharedPref
) : ViewModel() {

    fun getSkipStartScreen() = stateSharedPref.skipStartScreen
    fun getSkipRequestScreen() = stateSharedPref.skipRequestScreen

    fun setSkipStartScreen() {
        stateSharedPref.skipStartScreen = true
    }

    fun setSkipRequestScreen() {
        stateSharedPref.skipRequestScreen = true
    }

}