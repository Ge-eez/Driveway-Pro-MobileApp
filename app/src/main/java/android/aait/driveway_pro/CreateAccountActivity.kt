package android.aait.driveway_pro

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.aait.driveway_pro.Retrofit.MyService
import android.aait.driveway_pro.Retrofit.RetrofitClient
import android.aait.driveway_pro.databinding.ActivityCreateAccountBinding
import kotlinx.android.synthetic.main.activity_create_account.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.regex.Matcher
import java.util.regex.Pattern


class CreateAccountActivity : AppCompatActivity() {

    private var retrofit: Retrofit? = RetrofitClient.getInstance()
    private var retrofitInterface: MyService? = null
    private lateinit var binding: ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        retrofitInterface = retrofit!!.create(MyService::class.java)

        binding.createAccount.setOnClickListener {

            if (binding.name.text.toString().trim().isEmpty()) {
                binding.name.error = "Name Required"
                binding.name.requestFocus()
                return@setOnClickListener
            }
            if (binding.emailCreate.text.toString().trim().isEmpty()) {
                binding.emailCreate.error = "Email Required"
                binding.emailCreate.requestFocus()
                return@setOnClickListener
            }
            if (binding.phoneNo.text.toString().trim().isEmpty()) {
                binding.phoneNo.error = "Phone Number Required"
                binding.phoneNo.requestFocus()
                return@setOnClickListener
            }
            if (binding.password.text.toString().trim().isEmpty()) {
                binding.password.error = "Password Required"
                binding.password.requestFocus()
                return@setOnClickListener
            }
            if (binding.confirmPass.text.toString().trim().isEmpty()) {
                binding.confirmPass.error = "Password Required"
                binding.confirmPass.requestFocus()
                return@setOnClickListener
            }
            if(binding.plateNo.text.toString().length!=5){
                binding.plateNo.error="Plate Number must be five digits"
                binding.plateNo.requestFocus()
                return@setOnClickListener
            }
            if (binding.password.text.toString().trim().length <8 && !(isValidPassword(binding.password.text.toString().trim()))){
                binding.password.error="Password must be length of 8 digits and a strong one"
                binding.password.requestFocus()
                return@setOnClickListener
            }
            val map = HashMap<String, String>()

            map["name"] = binding.name.text.toString()
            map["email"] = binding.emailCreate.text.toString()
            map["password"] = binding.password.text.toString()
            map["phone_no"] = binding.phoneNo.text.toString()
            map["plate_number"]=binding.plateNo.text.toString()


            if (map["password"] == binding.confirmPass.text.toString()) {
                val call: Call<Void> = retrofitInterface!!.executeSignup(map)

                call.enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.code() == 200) {

                            Toast.makeText(this@CreateAccountActivity, "Signed Up successfully", Toast.LENGTH_LONG).show()
                            var intent= Intent(this@CreateAccountActivity,MainActivity::class.java)
                            startActivity(intent)
                        } else if (response.code() == 400) {

                            Toast.makeText(this@CreateAccountActivity, "Already Registered ", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Toast.makeText(
                            this@CreateAccountActivity, t.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                })
            } else {
                Toast.makeText(this, "Password must match", Toast.LENGTH_LONG).show()
            }

        }
        binding.haveAnAccount.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }

    }

    private fun isValidPassword(pass: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher

        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"

        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(pass)

        return matcher.matches()
    }
}