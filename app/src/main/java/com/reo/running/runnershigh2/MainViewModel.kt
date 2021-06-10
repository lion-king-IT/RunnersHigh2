package com.reo.running.runnershigh2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _isVisibleBottomNavigation = MutableLiveData<Boolean>()
    val isVisibleBottomNavigation: LiveData<Boolean>
        get() = _isVisibleBottomNavigation

    init {
        _isVisibleBottomNavigation.value = false
    }

}