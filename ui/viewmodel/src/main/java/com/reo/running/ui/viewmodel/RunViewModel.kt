package com.reo.running.ui.viewmodel

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RunViewModel : ViewModel() {
    private val _currentLatLng = MutableLiveData<Location>()
    val currentLatLng: LiveData<Location>
        get() = _currentLatLng

    private val _markerOptions = MutableLiveData<Location>()
    val makerOptions: LiveData<Location>
        get() = _markerOptions

    fun setLocation(latLng: Location) {
        _currentLatLng.value = latLng
    }

    fun getLocation(): Location? {
        return _currentLatLng.value
    }


}