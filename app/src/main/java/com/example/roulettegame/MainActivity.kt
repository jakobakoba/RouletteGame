package com.example.roulettegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.roulettegame.rule_screen.RuleScreen
import com.example.roulettegame.ui.theme.RouletteGameTheme
import com.example.roulettegame.ui.theme.roulettebackground

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: RuleViewModel
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
                    RuleScreen(viewModel = viewModel)
                }
            }
        }
    }
}

