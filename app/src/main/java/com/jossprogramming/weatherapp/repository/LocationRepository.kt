package com.jossprogramming.weatherapp.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class LocationRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @OptIn(ExperimentalCoroutinesApi::class)
    @SuppressLint("MissingPermission")
    suspend fun getLastKnownLocation(): Location? = suspendCancellableCoroutine { cont ->
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                cont.resume(location) {}
            }
            .addOnFailureListener { exception ->
                cont.resume(null) {}
            }

        cont.invokeOnCancellation {
            cont.resume(null) {}
        }
    }
}
