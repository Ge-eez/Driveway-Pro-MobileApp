package android.aait.driveway_pro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail_info.*
import kotlinx.android.synthetic.main.activity_timer.*
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

//        val intent = intent
//        slotDet.text=intent.getStringExtra("id")
//        plateNoDet.text=intent.getStringExtra("companyName")
//        startedDet.text=intent.getStringExtra("price")
//        exitTimeDet.text=intent.getStringExtra("exitAt")
//        totalPriceDet.text= intent.getDoubleExtra("total", 0.0).toString()
        val intent = intent
        startedDet.text = intent.getStringExtra("startTimeValue")
        val sdf = SimpleDateFormat("hh:mm")
        exitTimeDet.text = sdf.format(Date())
        plateNoDet.text = intent.getStringExtra("company")

    }
}