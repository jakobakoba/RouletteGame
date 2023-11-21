package com.bor96dev.roulettegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.bor96dev.roulettegame.rule_screen.RuleScreen
import com.bor96dev.roulettegame.ui.theme.RouletteGameTheme
import com.bor96dev.roulettegame.ui.theme.roulettebackground
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.interstitial.InterstitialAd

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: RuleViewModel
    private var mInterstitialAd: InterstitialAd? = null
    private final var TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RuleViewModel::class.java)
        setContent {
            RouletteGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = roulettebackground
                ) {
                    RuleScreen(viewModel = viewModel, this)
                }
            }
        }
        MobileAds.initialize(this) {}


    }
}


