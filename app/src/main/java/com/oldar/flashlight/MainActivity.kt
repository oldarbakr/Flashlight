package com.oldar.flashlight

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.camera2.CameraManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    //
    private lateinit var cameraM : CameraManager
    private lateinit var powerBtn: ImageButton
    private lateinit var mediaPlayer: MediaPlayer
    var isFlash = true

    lateinit var mAdView : AdView


    //
    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mediaPlayer = MediaPlayer.create(this, R.raw.bib)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        window.navigationBarColor = ContextCompat.getColor(this@MainActivity, R.color.black)

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)



        /**set view Id*/
        powerBtn = findViewById(R.id.power)
        cameraM = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        powerBtn.setOnClickListener { flashLightOnRoOff(it) }




        val cameraListId = cameraM.cameraIdList[0]
        cameraM.setTorchMode(cameraListId,true)
    }

    //
    @RequiresApi(Build.VERSION_CODES.M)
    private fun flashLightOnRoOff(v: View?) {

        try {
            mediaPlayer.start()
        } catch (e: Exception) {
        }


        /**set flash code*/
         if(isFlash){
            val cameraListId = cameraM.cameraIdList[0]
            cameraM.setTorchMode(cameraListId,false)
            isFlash = false
            powerBtn.setImageResource(R.drawable.ic_power_off)
        }
        else if (!isFlash){
            val cameraListId = cameraM.cameraIdList[0]
            cameraM.setTorchMode(cameraListId,true)
            isFlash = true
            powerBtn.setImageResource(R.drawable.ic_power_on)
        }

    }


}