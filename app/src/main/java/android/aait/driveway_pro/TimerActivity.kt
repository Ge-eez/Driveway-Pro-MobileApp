package android.aait.driveway_pro

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_timer.*
import kotlinx.android.synthetic.main.activity_timer.nameOfCompany
import kotlinx.android.synthetic.main.activity_timer.priceValue
import java.text.SimpleDateFormat
import java.util.*

class TimerActivity : AppCompatActivity() {
//    private var retrofit: Retrofit? = RetrofitClient.getInstance()
//    private var retrofitInterface: MyService? = null
//    private lateinit var sessionManager: SessionManager
//    private var resp = ArrayList<Slot>()
//    var listOfslot = mutableListOf<String>()
//    private lateinit var slotSlected:Any
//    private lateinit var mapboxMap: MapboxMap
//
//    private val  SOURCE_ID = "SOURCE_ID"
//    private val  ICON_ID = "ICON_ID"
//    private val  LAYER_ID = "LAYER_ID"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))
        setContentView(R.layout.activity_timer)
//        mapView?.onCreate(savedInstanceState)
//        mapView.getMapAsync(this)

//        retrofitInterface = retrofit!!.create(MyService::class.java)
//        sessionManager = SessionManager(this)

        val intent = intent
        var parkingId=intent.getStringExtra("parkingLotId")
        var parkingSlotId=intent.getStringExtra("parkingSlotId")
        var price=intent.getStringExtra("price")

        nameOfCompany.text=intent.getStringExtra("company")
        priceValue.text = price+" birr per hour"
//        var stoptime: Long = 0
//        var chronometer: Chronometer
//
//        chronometer = findViewById(R.id.chronom1)
//        chronometer.start()
        val sdf = SimpleDateFormat("hh:mm")
        startTimeValue.text = sdf.format(Date())

        doneBtn.setOnClickListener (object : View.OnClickListener {
//            var isWorking = false

            override fun onClick(v: View) {

//                if (!isWorking) {
//                    chronometer.start()
//                    isWorking = true
//                } else {
//                    chronometer.stop()
//                    isWorking = false
//                }


//            val map = HashMap<String, String>()
//            if (parkingId != null) {
//                map.put("parkingLotId", parkingId)
//            }
//            if (parkingSlotId != null) {
//                map.put("parkingSlotId",parkingSlotId)
//            }
//
//            Toast.makeText(this,"$parkingId $parkingSlotId", Toast.LENGTH_LONG).show()
//
//            val call = retrofitInterface!!.park("Bearer ${sessionManager.fetchAuthToken()}", map)
//            call.enqueue(object : Callback<Park> {
//
//                override fun onFailure(call: Call<Park>, t: Throwable) {
//
//                    Toast.makeText(this@TimerActivity, t.message, Toast.LENGTH_LONG).show()
//                }
//                override fun onResponse(call: Call<Park>, response: Response<Park>) {
//
//                    if (response.code() == 200) {
//                        var resp1=response.body()!!._id
//                        sessionManager.saveTicket(response.body()!!._id)
//                        Toast.makeText(this@TimerActivity," slot $parkingSlotId reservation successful", Toast.LENGTH_LONG).show()
//                        startActivity(Intent(this@BookActivity,TimerActivity::class.java))
                var intented= Intent(this@TimerActivity, ExitUser::class.java)

                intented.putExtra("parkingLotId",parkingId)
                intented.putExtra("parkingSlotId",parkingSlotId)
                intented.putExtra("price",price)
                startActivity(intented)
//                    }
//                    else if (response.code() == 400) {
//                        Toast.makeText(this@TimerActivity ,"Client Error ", Toast.LENGTH_LONG).show()
//                    }
//
//                }
//            })
//        }
        }




//        startBtn.setOnClickListener(object : View.OnClickListener {

//            var isWorking = false

//            override fun onClick(v: View) {

//                if (!isWorking) {
//                    chronometer.start()
//                    isWorking = true
//                } else {
//                    chronometer.stop()
//                    isWorking = false
//                }

//                startBtn.setText(if (isWorking) R.string.start else R.string.stop)
//
//                Toast.makeText(
//                    this@TimerActivity, getString(
//                        if (isWorking)
//                            R.string.working
//                        else
//                            R.string.stopped
//                    ),
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        })

    })


//    override fun onMapReady(mapboxMap: MapboxMap) {
//        val symbolLayers = ArrayList<Feature>()
//        symbolLayers.add(Feature.fromGeometry(Point.fromLngLat(38.0, 9.9)))
//
//        mapboxMap.setStyle(
//            Style.Builder().fromUri(Style.MAPBOX_STREETS)
//                .withImage(ICON_ID, BitmapUtils
//                    .getBitmapFromDrawable(ContextCompat.getDrawable(this, R.drawable.mapbox_marker_icon_default))!!)
//                .withSource(GeoJsonSource(SOURCE_ID, FeatureCollection.fromFeatures(symbolLayers)))
//                .withLayer(
//                    SymbolLayer(LAYER_ID, SOURCE_ID)
//                        .withProperties(
//                            PropertyFactory.iconImage(ICON_ID),
//                            PropertyFactory.iconSize(1.0f),
//                            PropertyFactory.iconAllowOverlap(true),
//                            PropertyFactory.iconIgnorePlacement(true)
//                        ))
//        )
//    }
//    public override fun onResume() {
//        super.onResume()
//        mapView?.onResume()
//    }
//
//    override fun onStart() {
//        super.onStart()
//        mapView?.onStart()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        mapView?.onStop()
//    }
//
//    public override fun onPause() {
//        super.onPause()
//        mapView?.onPause()
//    }
//
//    override fun onLowMemory() {
//        super.onLowMemory()
//        mapView?.onLowMemory()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mapView?.onDestroy()
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        mapView?.onSaveInstanceState(outState)
//    }


}
}