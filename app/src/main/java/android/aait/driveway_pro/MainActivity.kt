package android.aait.driveway_pro


import android.content.Intent
import android.os.Bundle

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

import android.aait.driveway_pro.Retrofit.MyService
import android.aait.driveway_pro.Retrofit.RetrofitClient
import android.aait.driveway_pro.databinding.ActivityMainBinding

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.*
import kotlin.collections.HashMap


class MainActivity : AppCompatActivity() {



    private var retrofit: Retrofit? = RetrofitClient.getInstance()
    private var retrofitInterface: MyService? = null

    private var sessionManager: SessionManager?=null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sessionManager=SessionManager(this)

        if(sessionManager?.fetchPassword()!=null && sessionManager?.fetchPassword()!=null && sessionManager?.fetchRole()=="user"){
            val intent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        else if(sessionManager?.fetchPassword()!=null && sessionManager?.fetchPassword()!=null && sessionManager?.fetchRole()=="parking_officer"){
            val intent = Intent(this@MainActivity, PoHomeActivity::class.java)
            startActivity(intent)
            //finish()
        }





        retrofitInterface = retrofit!!.create(MyService::class.java)



        binding.loginBtn.setOnClickListener{
            if (binding.usernameField.text.toString().trim().isEmpty()) {
                binding.usernameField.error = "Email Required"
                binding.usernameField.requestFocus()
                return@setOnClickListener
            }
            if (binding.passwordField.text.toString().trim().isEmpty()) {
                binding.passwordField.error = "Password Required"
                binding.passwordField.requestFocus()
                return@setOnClickListener
            }
            else{
                binding.progressBar.isVisible=true
            }
            val map = HashMap<String, String>()

            map["email"] = binding.usernameField.text.toString()
            map["password"] = binding.passwordField.text.toString()

            val call = retrofitInterface!!.executeLogin(map)
            sessionManager= SessionManager(this)

            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.code()==200 && response.body()!=null) {

                        sessionManager!!.saveAuthToken(response.body()!!.token)
                        sessionManager!!.saveRoles((response.body()!!.roles[0]))
                        sessionManager!!.saveEmail((response.body()!!.email))
                        sessionManager!!.savePassword((response.body()!!.password))
                        sessionManager!!.saveName(response.body()!!.name)


                        if(sessionManager?.fetchPassword()!=null && sessionManager?.fetchPassword()!=null && sessionManager?.fetchRole()=="user"){
                            val intent = Intent(this@MainActivity, HomeActivity::class.java)
                            intent.putExtra("email",binding.usernameField.text)
                            intent.putExtra("name",response.body()!!.name)
                            startActivity(intent)
                            finish()
                        }
                        else if( sessionManager?.fetchRole()=="parking_officer"){
                            val intent = Intent(this@MainActivity, PoHomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }





                    } else if (response.code() == 400) {

                        Toast.makeText(this@MainActivity, "User doesnt exist ", Toast.LENGTH_LONG)
                            .show()
                        binding.progressBar.isVisible=false
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
                    binding.progressBar.isVisible=false
                }
            })
        }
        binding.haveNoAccount.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
        binding.forgotPass.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }





    }


    override fun onStart() {
        super.onStart()

        sessionManager= SessionManager(this)
        if(sessionManager?.fetchPassword()!=null && sessionManager?.fetchPassword()!=null && sessionManager?.fetchRole()=="user"){
            val intent = Intent(this@MainActivity, HomeActivity::class.java)

            startActivity(intent)
            finish()
        }

    }


}
