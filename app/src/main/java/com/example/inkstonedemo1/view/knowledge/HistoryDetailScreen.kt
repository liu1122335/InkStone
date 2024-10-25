package com.example.inkstonedemo1.view.knowledge

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.model.HistoryDetailDestination
import com.example.inkstonedemo1.model.knowledge.HistoryKnowledge
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryDetailScreen(
    historyKnowledge: HistoryKnowledge,
    onBackButtonClicked : () -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = HistoryDetailDestination.tabName,fontFamily = FontFamily(Font(R.font.font_1)))
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackButtonClicked
                    ){
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                    }
                },
                actions = {
                    Icon(
                        painterResource(id = HistoryDetailDestination.icon),
                        modifier = Modifier.fillMaxHeight().padding(end = 20.dp),
                        contentDescription = ""
                    )
                }
            )
        }
    ){innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)){
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                item {
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = historyKnowledge.title,
                        fontSize = 25.sp,
                        fontFamily = FontFamily(Font(R.font.font_1))
                    )
                }
                item {
                    Text(
                        text = "简述",
                        fontSize = 23.sp,
                        modifier = Modifier.padding(top = 15.dp),
                        fontFamily = FontFamily(Font(R.font.font_1))
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
                        modifier = Modifier.padding(top = 20.dp),
                        fontFamily = FontFamily(Font(R.font.font_1))
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
                                    modifier = Modifier.padding(top = 10.dp),
                                    fontFamily = FontFamily(Font(R.font.font_1))
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}