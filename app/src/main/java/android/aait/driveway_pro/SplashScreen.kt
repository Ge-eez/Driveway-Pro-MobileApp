package android.aait.driveway_pro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {

    private lateinit var logoImageView: ShapeableImageView
    private lateinit var logoText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        logoImageView = findViewById(R.id.shapeableLogo)
        logoText = findViewById(R.id.logo_text)

        val imageAnimate = AnimationUtils.loadAnimation(this,R.anim.slide_logo)
        logoImageView.startAnimation(imageAnimate)


        logoText.startAnimation(imageAnimate)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2500)
    }

}