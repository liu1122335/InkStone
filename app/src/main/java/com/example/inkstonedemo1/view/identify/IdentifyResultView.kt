package com.example.inkstonedemo1.view.identify

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inkstonedemo1.ui.theme.identify_result_bg_color
import com.example.inkstonedemo1.component.VerticalText
import com.example.inkstonedemo1.data.allIdentifyData
import com.example.inkstonedemo1.model.InkStoneTypeKnowledge
import com.example.inkstonedemo1.ui.theme.main_surface_color
import dev.romainguy.kotlin.math.all

@Composable
fun IdentifyResultScreen(
    identifyBitmap : Bitmap,
    identifyResult : String
){

    val identifyKnowledge = getIdentifyKnowledge(identifyResult)
    Log.d("identifyResult",identifyResult)
    Log.d("identifyKnowledge",identifyKnowledge.type)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = main_surface_color),
        contentAlignment = Alignment.TopCenter
    ){
        LazyColumn(
            modifier = Modifier.padding(top = 50.dp,start = 25.dp, end = 25.dp, bottom = 25.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
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
            }
            item {
                Text(
                    text = identifyKnowledge.type,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    fontSize = 23.sp
                )

                Row (
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ){
                    VerticalText(
                        text = "描述",
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.width(30.dp))

                    Text(
                        text = identifyKnowledge.description,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            item {
                Row (
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ){
                    VerticalText(
                        text = "非遗传承人",
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.width(30.dp))

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        items(identifyKnowledge.representativeFigureName.size){
                            Column (
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(bottom = 10.dp)
                            ){
                                androidx.compose.material3.Card(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(15.dp))
                                        .size(180.dp)
                                        .padding(horizontal = 10.dp),
                                ) {
                                    Image(
                                        modifier = Modifier.fillMaxSize(),
                                        painter = painterResource(id = identifyKnowledge.representativeFigureAvatar[it]),
                                        contentDescription = "",
                                        contentScale = ContentScale.FillBounds
                                    )
                                }

                                androidx.compose.material3.Text(
                                    text = identifyKnowledge.representativeFigureName[it],
                                    modifier = Modifier.padding(top = 10.dp)
                                )
                            }
                        }
                    }
                }
            }

            item {
                Row (
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ){
                    VerticalText(
                        text = "相关名砚",
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.width(30.dp))

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        items(identifyKnowledge.representativeFigureName.size){
                            Column (
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(bottom = 10.dp)
                            ){
                                androidx.compose.material3.Card(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(15.dp))
                                        .size(180.dp)
                                        .padding(horizontal = 10.dp),
                                ) {
                                    Image(
                                        modifier = Modifier.fillMaxSize(),
                                        painter = painterResource(id = identifyKnowledge.relevancyInkStoneImage[it]),
                                        contentDescription = "",
                                        contentScale = ContentScale.FillBounds
                                    )
                                }

                                androidx.compose.material3.Text(
                                    text = identifyKnowledge.relevancyInkStoneName[it],
                                    modifier = Modifier.padding(top = 10.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun getIdentifyKnowledge(type : String): InkStoneTypeKnowledge = when(type){
    "端砚" -> allIdentifyData[0]
    "洮砚" -> allIdentifyData[1]
    "歙砚" -> allIdentifyData[2]
    "易水砚" -> allIdentifyData[3]
    "却砚" -> allIdentifyData[4]
    else -> {
        allIdentifyData[0]
    }
}
