package android.aait.driveway_pro

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.aait.driveway_pro.Retrofit.MyService
import android.aait.driveway_pro.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_spot_list.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SpotListActivity : AppCompatActivity(), NearestAdapter.ClickedItem {

    private var retrofit: Retrofit? = RetrofitClient.getInstance()
    private var retrofitInterface: MyService? = null
    private lateinit var sessionManager: SessionManager
    var spotData: ArrayList<Nearest1> = ArrayList()
    private var resp = ArrayList<Slot>()
    var arrayListslot =ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spot_list)

        val params = HashMap<String, Double>()
        params.put("longitude", 38.7)
        params.put("latitude", 8.9)

        retrofitInterface = retrofit!!.create(MyService::class.java)
        sessionManager = SessionManager(this)
        var recyclerView: RecyclerView = rcSpots
        recyclerView.layoutManager = LinearLayoutManager(this)

        var call = retrofitInterface!!.findspot1("Bearer ${sessionManager.fetchAuthToken()}", params)
//Auth, Json
        call.enqueue(object : Callback<ArrayList<Nearest1>> {
            override fun onFailure(call: Call<ArrayList<Nearest1>>, t: Throwable) {
                Toast.makeText(this@SpotListActivity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<ArrayList<Nearest1>>,
                response: Response<ArrayList<Nearest1>>
            ) {
                if (response.code() == 200 && response.body() != null) {
                    var resp = response.body()!!
                    recyclerView.adapter = NearestAdapter(resp,this@SpotListActivity)

                } else if (response.code() == 400) {

                    Toast.makeText(this@SpotListActivity, "client error", Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    override fun clickedSpot(nearest: Nearest1) {
        var callback:Callback<ArrayList<Slot>>
        var intent= Intent(this, slotListActivity::class.java)
        var parkingId = nearest._id
        var price = nearest.price
        var company = nearest.company

        intent.putExtra("id", parkingId)//trying to send the id first then do the ntk call on the other activity
        intent.putExtra("price", price.toString())
        intent.putExtra("company", company.toString())

        startActivity(intent)
        finish()
    }

}