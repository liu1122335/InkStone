package com.example.inkstonedemo1.view.identify

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.identify_result_bg_color
import com.example.inkstonedemo1.component.VerticalText

@Composable
fun IdentifyResultScreen(
    identifyBitmap : Bitmap,
    identifyResult : String
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = identify_result_bg_color),
        contentAlignment = Alignment.TopCenter
    ){

        Column(
            modifier = Modifier.padding(top = 50.dp,start = 25.dp, end = 25.dp, bottom = 25.dp)
        ) {
            Card (
                modifier = Modifier
                    .size(400.dp)
                    .clip(RoundedCornerShape(15.dp)),
                elevation = 5.dp
            ){
                Image(
                    bitmap = identifyBitmap.asImageBitmap(),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )
            }
            Text(
                text = identifyResult,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                fontSize = 23.sp
            )

            Row (
                modifier = Modifier.padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ){
                VerticalText(
                    text = identifyResult,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.width(30.dp))

                Text(
                    text = "啊打发发达手动阀手动阀手动阀手动阀撒旦阿发是否大撒旦发射点发生啊发射点发生发生",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}