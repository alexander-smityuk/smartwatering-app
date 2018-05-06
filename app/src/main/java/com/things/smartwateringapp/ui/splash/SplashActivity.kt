package com.things.smartwateringapp.ui.splash

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import com.things.smartwateringapp.R
import com.things.smartwateringapp.ui.BaseActivity
import com.things.smartwateringapp.ui.main.MainActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    private val layoutResId: Int = R.layout.activity_splash

    private val splashTime: Long = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(layoutResId)

        Handler().postDelayed({
            startActivity<MainActivity>()
        }, splashTime)
    }
}
