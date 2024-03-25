package com.example.inkstonedemo1.view.knowledge

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inkstonedemo1.model.knowledge.HistoryKnowledge
@Composable
fun HistoryDetailScreen(
    historyKnowledge: HistoryKnowledge
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        item {
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = historyKnowledge.title,
                fontSize = 25.sp
            )
        }
        item {
            Text(
                text = "简述",
                fontSize = 23.sp,
                modifier = Modifier.padding(top = 15.dp)
            )
        }
        item {
            Text(
                text = historyKnowledge.content,
                modifier = Modifier.padding(top = 15.dp),
            )
        }
        item {
            Text(
                text = "相关砚台",
                fontSize = 23.sp,
                modifier = Modifier.padding(top = 20.dp)
            )

        }
        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
            ){
                items(historyKnowledge.relevancyImage.size){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(bottom = 10.dp)
                    ){
                        Card(
                            modifier = Modifier
                                .clip(RoundedCornerShape(15.dp))
                                .size(180.dp)
                                .padding(horizontal = 10.dp),
                        ){
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(id = historyKnowledge.relevancyImage[it]),
                                contentDescription = "",
                                contentScale = ContentScale.FillBounds
                            )
                        }

                        Text(
                            text = historyKnowledge.relevancyName[it],
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }
                }
            }
        }
    }
}