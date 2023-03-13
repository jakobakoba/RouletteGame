package com.example.roulettegame.rule_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roulettegame.R


@Composable
fun RuleScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "0",
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
            )
            Image(
                painter = painterResource(id = R.drawable.strelka),
                contentDescription = "Pointer",
                modifier = Modifier
                    .fillMaxSize()
            )

        }
        androidx.compose.material.Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp)
                .padding(20.dp)


        ) {
            Text(
                text = "Start",
                color = Color.White,
                fontSize = 30.sp

            )
        }

    }

}