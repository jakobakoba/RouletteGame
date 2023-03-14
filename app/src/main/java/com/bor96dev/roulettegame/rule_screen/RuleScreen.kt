package com.bor96dev.roulettegame.rule_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bor96dev.roulettegame.R
import com.bor96dev.roulettegame.RuleViewModel
import com.bor96dev.roulettegame.utils.NumberUtil
import kotlin.math.roundToInt


@Composable
fun RuleScreen(viewModel: RuleViewModel) {

    var rotationValue by remember {
        mutableStateOf(viewModel.rotationValue)
    }

    var number by remember {
        mutableStateOf(viewModel.number)
    }
    val angle: Float by animateFloatAsState(
        targetValue = rotationValue,
        animationSpec = tween(
            durationMillis = 4000
        ),
        finishedListener = {
            val index = (360f - (it % 360)) / (360f / NumberUtil.list.size)
            number = NumberUtil.list[index.roundToInt()]
            viewModel.number = number
            viewModel.rotationValue = rotationValue

        }

    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
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