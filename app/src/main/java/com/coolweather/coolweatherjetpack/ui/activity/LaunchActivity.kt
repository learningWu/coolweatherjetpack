package com.coolweather.coolweatherjetpack.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.airbnb.lottie.LottieAnimationView
import com.coolweather.coolweatherjetpack.Function1
import com.coolweather.coolweatherjetpack.R
import com.coolweather.coolweatherjetpack.ui.MainActivity
import com.coolweather.coolweatherjetpack.util.timer
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

class LaunchActivity : AppCompatActivity() {
    @BindView(R.id.animation_view)
    lateinit var lottieAnimationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.statusBarColor = Color.TRANSPARENT
        }
        setContentView(R.layout.activity_launch)
        ButterKnife.bind(this)
        Executors.newSingleThreadExecutor().submit(Runnable {
            Thread.sleep(2000)
            finish()
            startActivity(Intent(this@LaunchActivity, MainActivity::class.java))
        })
    }
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }
}