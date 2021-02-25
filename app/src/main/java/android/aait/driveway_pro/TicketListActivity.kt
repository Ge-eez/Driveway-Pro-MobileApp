package android.aait.driveway_pro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.aait.driveway_pro.Retrofit.MyService
import android.aait.driveway_pro.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_slot_list.*
import kotlinx.android.synthetic.main.activity_ticket_list.*
import kotlinx.android.synthetic.main.app_bar_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import kotlinx.android.synthetic.main.app_bar_main.toolbar as toolbar1

class TicketListActivity : AppCompatActivity(), TicketAdapter.ClickedItem {

    private var retrofit: Retrofit? = RetrofitClient.getInstance()
    private var retrofitInterface: MyService? = null
    private lateinit var sessionManager: SessionManager
    private var resp = ArrayList<Ticket>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_list)

        // set toolbar as support action bar


        supportActionBar?.apply {
            title = "Active Tickets"

            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        retrofitInterface = retrofit!!.create(MyService::class.java)
        sessionManager = SessionManager(this)

        var recyclerView: RecyclerView = rcTicket
        recyclerView.layoutManager = LinearLayoutManager(this)


        var call = retrofitInterface!!.getTicket("Bearer ${sessionManager.fetchAuthToken()}")

        call.enqueue(object : Callback<ArrayList<Ticket>> {
            override fun onFailure(call: Call<ArrayList<Ticket>>, t: Throwable) {
                Toast.makeText(this@TicketListActivity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ArrayList<Ticket>>, response: Response<ArrayList<Ticket>>) {

                resp = response.body()!!

                recyclerView.adapter = TicketAdapter(resp,this@TicketListActivity)
            }
        })
    }

    override fun clickedSpot(ticket: Ticket) {
        TODO("Not yet implemented")
    }
}