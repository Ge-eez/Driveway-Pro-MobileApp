package android.aait.driveway_pro

import android.aait.driveway_pro.Retrofit.MyService
import android.aait.driveway_pro.Retrofit.RetrofitClient
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_po_home.*
import kotlinx.android.synthetic.main.activity_timer.*
import kotlinx.android.synthetic.main.activity_timer.nameOfCompany
import kotlinx.android.synthetic.main.activity_timer.priceValue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*

class TimerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val intent = intent
        var parkingId=intent.getStringExtra("parkingLotId")
        var parkingSlotId=intent.getStringExtra("parkingSlotId")
        var price=intent.getStringExtra("price")
        var company = intent.getStringExtra("company")

        nameOfCompany.text=company.toString()
        priceValue.text = price+" birr per hour"

        val sdf = SimpleDateFormat("hh:mm")
        val startTime = sdf.format(Date())
        val startingTIme = startTime?.split(":")
        val starter = "${startingTIme?.get(0)}.${startingTIme?.get(1)}"
        startTimeValue.text = startTime

        doneBtn.setOnClickListener {
            val intent2 = Intent(this@TimerActivity, DetailActivity::class.java)
            intent2.putExtra("parkingLotId",parkingId)
            intent2.putExtra("parkingSlotId",parkingSlotId)
            intent2.putExtra("startTimeValue",starter)
            intent2.putExtra("company",company)
            intent2.putExtra("price",price)
            startActivity(intent2)
            finish()
            }

    }
}