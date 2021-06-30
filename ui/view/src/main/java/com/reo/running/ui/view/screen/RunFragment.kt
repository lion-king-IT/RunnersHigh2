package com.reo.running.ui.view.screen

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.reo.running.ui.view.databinding.FragmentRunBinding
import com.reo.running.ui.viewmodel.MainViewModel
import com.reo.running.ui.viewmodel.RunViewModel

class RunFragment : Fragment(), OnMapReadyCallback {
    private val parentViewModel: MainViewModel by activityViewModels()
    private val runViewModel: RunViewModel by viewModels()

    private val fusedLocationProviderClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireContext())
    }

    val sydney = LatLng(-33.852, 151.211)
    private lateinit var binding: FragmentRunBinding

    companion object {
        private const val LOCATION_REQUEST = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentRunBinding.inflate(layoutInflater, container, false)
        binding.run {
            lifecycleOwner = viewLifecycleOwner
            viewModel = runViewModel
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            mapDeployment(this, savedInstanceState)
            Log.d("debug", "after-mapDeployment")
        }
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    private fun mapDeployment(binding: FragmentRunBinding, savedInstanceState: Bundle?) {
        binding.run {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_REQUEST
                )
                return
            }

            mapView.onCreate(savedInstanceState)

            val locationRequest = LocationRequest.create().apply {
                interval = 500
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }

            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    runViewModel.setLocation(locationResult.lastLocation)
                }
            }

            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest, locationCallback,
                Looper.getMainLooper()
            )

            mapView.getMapAsync(this@RunFragment)

        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.let {
            it.addMarker(MarkerOptions().position(sydney))
            it.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }

    }

}