package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import  android.widget.Button
import android.hardware.Sensor
import android.hardware.SensorManager
import android.hardware.SensorEvent
import  android.hardware.SensorEventListener
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.lang.NullPointerException

class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    var boolVal = 0

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (boolVal == 0) {
            textView.text =
                "X = ${event!!.values[0]}\n\n" + "Y = ${event.values[1]}\n\n" + "Z= ${event.values[2]}\n\n"
        } else {
            textView.text = ""
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



       start_button.setOnClickListener {
           boolVal = 0
           sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
           if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
               sensorManager.registerListener(
                   this,
                   sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)  ,
                   SensorManager.SENSOR_DELAY_NORMAL
               )

           }
           else{
               textView.text = "Sensor Not Available"
           }
       }

       stop_button.setOnClickListener {
           boolVal = 1
       }

    }


}
