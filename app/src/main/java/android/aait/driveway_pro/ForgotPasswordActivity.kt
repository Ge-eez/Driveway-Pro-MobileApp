package android.aait.driveway_pro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_main.*

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        send_link_btn.setOnClickListener {
            if (email_send_link.text.toString().isEmpty()) {
                email_send_link.error = "Email Required"
                email_send_link.requestFocus()
                return@setOnClickListener
            }
            else{
                Toast.makeText(this@ForgotPasswordActivity, "Our api doesn't support updating password yet", Toast.LENGTH_LONG).show()
                var intent= Intent(this@ForgotPasswordActivity,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}