package com.example.inkstonedemo1.view.knowledge

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.inkstonedemo1.ui.theme.md_theme_light_primaryContainer
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.data.allCraftsmanKnowledge
import com.example.inkstonedemo1.model.CraftsmanKnowledgeDestination
import com.example.inkstonedemo1.model.knowledge.Craftsman
import com.example.inkstonedemo1.ui.theme.main_surface_color


/*
* 暴雨即将发生，现在在地面上有n根宽度为1的柱子，可以用n个非负整数分别表示每个宽度为1的柱子的高度，柱子在地面上按一字排开，假设每一个1*1的方格中所接的水为1个单位的雨水，要计算按这种方式排列的柱子在下雨后最多能接多少雨水（假设雨水足够多）。
在求出所能接的最大雨水之前，要先求出每个柱子所能接的最大雨水量，而当后面的柱子高度比前面的低时，是无法接雨水的，为了预防洪涝灾害，请设计一个算法用尽可能少的时间去计算出这些柱子的最大接水量。
*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CraftsmanKnowledgeScreen(
    onBackButtonClicked: () -> Unit
){
    var isDetailed by remember { mutableStateOf(false) }
    var currentCraftsman by remember { mutableStateOf(allCraftsmanKnowledge[0]) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = CraftsmanKnowledgeDestination.tabName,fontFamily = FontFamily(Font(R.font.font_1)))
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
                        painterResource(id = CraftsmanKnowledgeDestination.icon),
                        modifier = Modifier.fillMaxHeight().padding(end = 20.dp),
                        contentDescription = ""
                    )
                }
            )
        }
    ){innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
                .paint(painter = painterResource(id = R.drawable.bg_element_knowledge_screen))
        ){
            LazyColumn(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(allCraftsmanKnowledge){
                    CraftsmanCard(
                        craftsman = it,
                        onClick = {
                            isDetailed = !isDetailed
                            currentCraftsman = it
                        }
                    )
                }
            }
            AnimatedVisibility(
                visible = isDetailed,
                modifier = Modifier.fillMaxSize()
            ){
                Dialog(
                    onDismissRequest = {
                        isDetailed = !isDetailed
                    },
                ){
                    CraftsmanDetailCrad(currentCraftsman)
                }
            }
        }
    }
}

@Composable
fun CraftsmanCard(
    craftsman: Craftsman,
    onClick : () -> Unit
){
    Card(
        modifier = Modifier.fillMaxWidth().height(140.dp)
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
            .padding(horizontal = 5.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(color = md_theme_light_primaryContainer)
        ){
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colorStops = arrayOf(
                                Pair(0.4f, main_surface_color),
                                Pair(1f, Transparent)
                            )
                        )
                    )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = craftsman.avatar),
                    contentDescription = "",
                    modifier = Modifier.fillMaxHeight()
                        .size(140.dp),
                    contentScale = ContentScale.FillBounds
                )

                Spacer(modifier = Modifier.width(15.dp))

                Column(
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                ) {
                    Text(
                        text = craftsman.name,
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.font_1))
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = craftsman.introduce,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = FontFamily(Font(R.font.font_1))
                    )
                }
            }
        }
    }
}

@Composable
fun CraftsmanDetailCrad(
    craftsman : Craftsman
){
    Card(
        modifier = Modifier.fillMaxSize()
            .padding(vertical = 120.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .height(240.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.bg_craftsman_detail),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )

                    Box(
                        modifier = Modifier.fillMaxSize()
                            .background(
                            brush = Brush.verticalGradient(
                                colorStops = arrayOf(
                                    Pair(0.4f,Transparent),
                                    Pair(1f, Color.White)
                                )
                            )
                        )
                    )

                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = craftsman.avatar),
                            contentDescription = null,
                            modifier = Modifier.size(100.dp)
                                .clip(CircleShape)
                                .border(width = 3.dp, color = Color.Black, shape = CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = craftsman.name,
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.font_1))
                        )
                    }
                }
            }
            item {
                Text(
                    text = craftsman.introduce,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    fontFamily = FontFamily(Font(R.font.font_1))
                )
            }
            item {
                Text(
                    text = craftsman.information,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    fontFamily = FontFamily(Font(R.font.font_1))
                )
            }
        }
    }
}