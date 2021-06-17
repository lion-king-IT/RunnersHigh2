package com.reo.running.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.LocationServices

class MainViewModel : ViewModel() {

    private val _isVisibleToolbar = MutableLiveData<Boolean>(false)
    val isVisibleToolbar: LiveData<Boolean>
        get() = _isVisibleToolbar

    private val _isVisibleBottomNavigation = MutableLiveData<Boolean>(false)
    val isVisibleBottomNavigation: LiveData<Boolean>
        get() = _isVisibleBottomNavigation

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


}