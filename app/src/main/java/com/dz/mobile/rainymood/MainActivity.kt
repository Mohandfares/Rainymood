package com.dz.mobile.rainymood

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.dz.mobile.rainymood.databinding.ActivityMainBinding
import com.github.matteobattilana.weather.PrecipType
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<RainyViewModel>()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
            )
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btplay.setOnClickListener {
                viewModel.playStateChange()
            }
            lifecycleScope.launchWhenStarted {
                viewModel.playState.collectLatest { state ->
                    when (state) {
                        true -> {
                            btplay.setImageResource(R.drawable.twotone_pause_circle)
                            weatherView.setWeatherData(PrecipType.RAIN)
                            startService(Intent(this@MainActivity,PlaySoundService::class.java))
                        }
                        else -> {
                            btplay.setImageResource(R.drawable.twotone_play_circle)
                            weatherView.setWeatherData(PrecipType.CLEAR)
                            stopService(Intent(this@MainActivity,PlaySoundService::class.java))
                        }
                    }
                }
            }
        }
    }
}