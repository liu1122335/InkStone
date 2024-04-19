package com.example.inkstonedemo1.view.knowledge

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.component.Banner
import com.example.inkstonedemo1.data.allBannerImages
import com.example.inkstonedemo1.model.AllInkStoneListDestination
import com.example.inkstonedemo1.model.AppreciateKnowledgeDestination
import com.example.inkstonedemo1.model.CraftsmanKnowledgeDestination
import com.example.inkstonedemo1.model.DetailInformationDestination
import com.example.inkstonedemo1.model.HistoryKnowledgeDestination
import com.example.inkstonedemo1.room.inkstone.InkStone
import com.example.inkstonedemo1.ui.theme.main_surface_color

@Composable
fun KnowledgeScreen(
    allARInkStone: List<InkStone>,
    onDetailClicked: (String) -> Unit,
    onInkStoneCardClicked: (String,Int) -> Unit
){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        MainKnowledgeScreen(
            allARInkStone = allARInkStone,
            onInkStoneCardClicked = onInkStoneCardClicked,
            onDetailClicked = onDetailClicked
        )
    }
}

@Composable
fun MainKnowledgeScreen(
    allARInkStone: List<InkStone>,
    onInkStoneCardClicked: (String,Int) -> Unit,
    onDetailClicked: (String) -> Unit
){
    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .statusBarsPadding()
            .paint(painter = painterResource(id = R.drawable.bg_main_knowledge)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Banner(
                images = allBannerImages
            )
        }
        item {
            AllKnowledgeRow(onDetailClicked = onDetailClicked)
        }
        item {
            ShowInkStoneRow(
                allARInkStone = allARInkStone,
                onInkStoneCardClicked = onInkStoneCardClicked,
                onDetailClicked = onDetailClicked
            )
        }
    }
}

@Composable
fun AllKnowledgeRow(
    onDetailClicked: (String) -> Unit
){
    Box(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
            .height(210.dp)
    ){
        Card(
            modifier = Modifier.fillMaxHeight()
                .fillMaxWidth(0.30f)
                .align(Alignment.CenterStart)
                .clickable(
                    onClick = {
                        onDetailClicked(HistoryKnowledgeDestination.route)
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ),
            colors = CardDefaults.cardColors(containerColor = main_surface_color),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.history_knowledge_relevancy_1),
                    contentDescription = "",
                    modifier = Modifier.size(80.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "历史"
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxHeight()
                .fillMaxWidth(0.60f)
                .align(Alignment.CenterEnd)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth()
                    .clickable(
                        onClick = { onDetailClicked(AppreciateKnowledgeDestination.route) },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ),
                colors = CardDefaults.cardColors(containerColor = main_surface_color),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.appreciate_knowledge_cover_1),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .size(80.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "工艺"
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier.fillMaxWidth()
                    .clickable(
                        onClick = { onDetailClicked(CraftsmanKnowledgeDestination.route) },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ),
                colors = CardDefaults.cardColors(containerColor = main_surface_color),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.craftsman_avatar_1),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .size(80.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop

                    )
                    Text(
                        text = "名匠"
                    )
                }
            }
        }
    }
}
@Composable
fun ShowInkStoneRow(
    allARInkStone: List<InkStone>,
    onInkStoneCardClicked: (String,Int) -> Unit,
    onDetailClicked: (String) -> Unit
){
    Card(
        modifier = Modifier.padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(400.dp),
        colors = CardDefaults.cardColors(containerColor = main_surface_color),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Row(
            modifier = Modifier.padding(all = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "名砚", fontSize = 26.sp)
            IconButton(
                onClick = { onDetailClicked(AllInkStoneListDestination.route) }
            ){
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "")
            }
        }
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = allARInkStone[0].intactImageId),
                    contentDescription = "",
                    modifier = Modifier.size(140.dp).clip(RoundedCornerShape(10.dp))
                        .clickable {
                            onInkStoneCardClicked(DetailInformationDestination.route,0)
                        },
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = painterResource(id = allARInkStone[1].intactImageId),
                    contentDescription = "",
                    modifier = Modifier.size(140.dp).clip(RoundedCornerShape(10.dp))
                        .clickable {
                            onInkStoneCardClicked(DetailInformationDestination.route,1)
                        },
                    contentScale = ContentScale.Crop
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = allARInkStone[2].intactImageId),
                    contentDescription = "",
                    modifier = Modifier.size(140.dp).clip(RoundedCornerShape(10.dp))
                        .clickable {
                            onInkStoneCardClicked(DetailInformationDestination.route,2)
                        },
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = painterResource(id = allARInkStone[3].intactImageId),
                    contentDescription = "",
                    modifier = Modifier.size(140.dp).clip(RoundedCornerShape(10.dp))
                        .clickable {
                            onInkStoneCardClicked(DetailInformationDestination.route,3)
                        },
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}