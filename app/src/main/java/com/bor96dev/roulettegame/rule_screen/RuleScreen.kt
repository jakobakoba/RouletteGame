package com.bor96dev.roulettegame.rule_screen

import android.app.Activity
import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.bor96dev.roulettegame.R
import com.bor96dev.roulettegame.RuleViewModel
import com.bor96dev.roulettegame.utils.NumberUtil
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlin.math.roundToInt

fun showInterstitialAd(activity: Activity) {
    InterstitialAd.load(
        activity.applicationContext,
        "ca-app-pub-5225934313122408/5412804310",
        AdRequest.Builder().build(),
        object: InterstitialAdLoadCallback(){
            override fun onAdFailedToLoad(p0: LoadAdError) {
            }

            override fun onAdLoaded(p0: InterstitialAd) {
                p0.show(activity)
            }
        }
    )
}


@Composable
fun RuleScreen(viewModel: RuleViewModel, activity: Activity) {

    val context = LocalContext.current
    var rotationValue by remember {
        mutableStateOf(viewModel.rotationValue)
    }

    var number by remember {
        mutableStateOf(viewModel.number)
    }

    var mediaPlayer by remember {
        mutableStateOf(MediaPlayer())
    }
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

    val angle: Float by animateFloatAsState(
        targetValue = rotationValue,
        animationSpec = tween(
            durationMillis = 4000,
        ),
        finishedListener = {

            mediaPlayer.stop()
            val index = (360f - (it % 360)) / (360f / NumberUtil.list.size)
            number = NumberUtil.list[index.roundToInt()]
            viewModel.number = number
            viewModel.rotationValue = rotationValue

        },


        )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AdmobBanner(modifier = Modifier.fillMaxWidth())
        Text(
            text = number.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 60.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .wrapContentHeight()
                .wrapContentWidth()
        )
        Box(
            modifier = Modifier
                .weight(1F)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.roulette),
                contentDescription = "Roulette",
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(angle)
            )
            Image(
                painter = painterResource(id = R.drawable.strelka),
                contentDescription = "Pointer",
                modifier = Modifier
                    .fillMaxSize()
            )

        }
        androidx.compose.material.Button(
            onClick = {
                viewModel.showAdCounter++

                if (viewModel.showAdCounter == 8){
                    viewModel.showAdCounter = 0
                    showInterstitialAd(activity = activity)
                }




                mediaPlayer.release()
                mediaPlayer = MediaPlayer.create(context, R.raw.sound).apply {
                    setAudioAttributes(
                        AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build()
                    )
                }
                mediaPlayer.start()
                rotationValue = (720..1080).random().toFloat() + angle
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp)
                .padding(20.dp)


        ) {
            Text(
                text = "Spin the wheel",
                color = Color.White,
                fontSize = 30.sp

            )
        }



    }


}




@Composable
fun AdmobBanner(modifier: Modifier = Modifier) {
    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = {context ->
            AdView(context).apply{
                setAdSize(AdSize.FULL_BANNER)

                adUnitId = "ca-app-pub-5225934313122408/1481868987"

                loadAd(AdRequest.Builder().build())
            }
        })
}
