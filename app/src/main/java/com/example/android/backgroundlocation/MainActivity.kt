package com.example.android.backgroundlocation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val BACKGROUND_LOCATION_REQUEST_CODE=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        requestBackgroundLocation()
    }

    private fun requestBackgroundLocation(){
        if(isBackgroundLocationIsGranted())
            return
        else{
            ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION),
            BACKGROUND_LOCATION_REQUEST_CODE)
        }

    }

    private fun isBackgroundLocationIsGranted()
    = ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_BACKGROUND_LOCATION) ==
            PackageManager.PERMISSION_GRANTED

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode==BACKGROUND_LOCATION_REQUEST_CODE && grantResults.size>1
            && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"Granted",Toast.LENGTH_SHORT)
        else
            Toast.makeText(this,"Denied",Toast.LENGTH_SHORT)

    }
}