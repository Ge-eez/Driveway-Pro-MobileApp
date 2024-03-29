package android.aait.driveway_pro

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.aait.driveway_pro.Retrofit.MyService
import android.aait.driveway_pro.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_po_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class PoHomeActivity : AppCompatActivity() {

    private var retrofit: Retrofit? = RetrofitClient.getInstance()
    private var retrofitInterface: MyService? = null
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(android.aait.driveway_pro.R.layout.activity_po_home)

        retrofitInterface = retrofit!!.create(MyService::class.java)
        sessionManager = SessionManager(this)

        poName.text="Parking Officer: ${sessionManager.fetchName()}"

        exitBtn.setOnClickListener {
            if (plateNoInput.text.toString().trim().length <5){
                plateNoInput.error="Plate Number is a minimum of 5 characters"
                plateNoInput.requestFocus()
                return@setOnClickListener
            }

            val map = HashMap<String, String>()
            map["plate_no"] = plateNoInput.text.toString()

            var call = retrofitInterface!!.poCancel("Bearer ${sessionManager.fetchAuthToken()}",map)

            call.enqueue(object : Callback<PoResponse> {
                override fun onFailure(call: Call<PoResponse>, t: Throwable) {
                    Toast.makeText(this@PoHomeActivity, t.message, Toast.LENGTH_LONG).show()

                }

                override fun onResponse(call: Call<PoResponse>, response: Response<PoResponse>) {
                    if (response.code() == 200 ) {
                        var resp = response.body()!!
                        Toast.makeText(this@PoHomeActivity,"cleared",Toast.LENGTH_LONG).show()
                        var intent= Intent(this@PoHomeActivity, DetailInfoActivity::class.java)
                        intent.putExtra("slotId",resp.slot_id)
                        intent.putExtra("plateNumber",resp.plate_number)
                        intent.putExtra("parkAt",resp.park_at)
                        intent.putExtra("exitAt",resp.exit_at)
                        intent.putExtra("total",resp.total_price)
                        startActivity(intent)


                    } else if (response.code() == 400) {

                        Toast.makeText(this@PoHomeActivity, "Wrong Value", Toast.LENGTH_LONG)
                            .show()
                    }
                    else if(response.code()==404){
                        Toast.makeText(this@PoHomeActivity, "No Ticket found for plate number ${plateNoInput.text.toString()}", Toast.LENGTH_LONG).show()
                    }
                }
            })
        }

        parkBtn.setOnClickListener {
            if (plateNoInput.text.toString().trim().isEmpty()) {
                plateNoInput.error = "Plate Number Required"
                plateNoInput.requestFocus()
                return@setOnClickListener
            }
            if (plateNoInput.text.toString().trim().length <5){
                plateNoInput.error="Plate Number is a minimum of 5 characters"
                plateNoInput.requestFocus()
                return@setOnClickListener
            }

            val intent1=Intent(this, PoSlotListActivity::class.java)
            var plate_number=plateNoInput.text.toString()

            val intent = Intent(this, PoStackListActivity::class.java)
            intent.putExtra("plate_number",plate_number)
            startActivity(intent)

        }

        clearBtn.setOnClickListener {
            startActivity(Intent(this, PoClearStackActivity::class.java))
        }

        ticketBtn.setOnClickListener {
            startActivity(Intent(this, TicketListActivity::class.java))
        }

    }

}