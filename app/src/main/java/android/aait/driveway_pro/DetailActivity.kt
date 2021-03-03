package android.aait.driveway_pro

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail_info.*
import kotlinx.android.synthetic.main.activity_timer.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

//        val intent = intent

//        plateNoDet.text=intent.getStringExtra("companyName")
//        startedDet.text=intent.getStringExtra("price")
//        exitTimeDet.text=intent.getStringExtra("exitAt")

        val intent = intent
        val starter = intent.getStringExtra("startTimeValue")?.toFloat()
//        val startingTIme = startTime?.split(":")
//        val starter = "$startingTIme[0].$startingTIme[1]".toFloat()
        val sdf = SimpleDateFormat("hh:mm")
        val endTime = sdf.format(Date())
        val endingTime = endTime.split(":")
        val ender = "${endingTime[0]}.${endingTime[1]}".toFloat()

        val totalTime = if (ender> starter!!) ender- starter!! else starter-ender
        val pricePerHour = intent.getStringExtra("price")
        val totalPrice = (pricePerHour?.toInt() ?: 0) * totalTime

        slotDet.text = intent.getStringExtra("company")
        plateNoDet.text = "okay"
        exitTimeDet.text = ender.toString()
        startedDet.text = starter.toString()
        totalPriceDet.text= totalPrice.toString()





    }

}