package com.reo.running.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _isVisibleToolbar = MutableLiveData<Boolean>(false)
    val isVisibleToolbar: LiveData<Boolean>
        get() = _isVisibleToolbar

    private val _isVisibleBottomNavigation = MutableLiveData<Boolean>(true)
    val isVisibleBottomNavigation: LiveData<Boolean>
        get() = _isVisibleBottomNavigation

}