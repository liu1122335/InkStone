package com.example.inkstonedemo1.view.detail

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.component.ScrollableAppBar
import com.example.inkstonedemo1.component.VerticalText
import com.example.inkstonedemo1.data.InitInkStoneData
import com.example.inkstonedemo1.model.ARShowDestination
import com.example.inkstonedemo1.model.ImageShowDestination
import com.example.inkstonedemo1.model.MainInformationDestination
import com.example.inkstonedemo1.room.InkStone
import com.example.inkstonedemo1.viewmodel.DetailInformationScreenViewModel
import com.example.inkstonedemo1.viewmodel.MainScreenViewModel

@Composable
fun DetailInformationScreen(
    mainNavController: NavController,
    currentColor: Color,
    inkStone: InkStone,
    detailInformationScreenViewModel: DetailInformationScreenViewModel = DetailInformationScreenViewModel(
        inkStone = inkStone,
    ),
    mainScreenViewModel: MainScreenViewModel
){

    val detailInformationScreenUiState by detailInformationScreenViewModel.uiState.collectAsState()
    val navController = rememberNavController()
    val relevancyInkStoneList by detailInformationScreenViewModel.getRelevancyInkStoneList(inkStone.inkStoneType).collectAsState(
        initial = InitInkStoneData
    )
    var relevancyInkStoneList2 = relevancyInkStoneList
    relevancyInkStoneList2 -= inkStone

    NavHost(
        navController = navController,
        startDestination = MainInformationDestination.route,
        builder = {
            composable(route = MainInformationDestination.route){
                MainInformationScreen(
                    currentNavController = navController,
                    mainNavController = mainNavController,
                    currentColor = currentColor,
                    currentImage = detailInformationScreenUiState.inkStone.imageId,
                    onCollectedChanged = {
                        detailInformationScreenViewModel.updateInkStoneIsCollected(isCollected = it,mainScreenViewModel)
                    },
                    isCollected = detailInformationScreenUiState.inkStone.isCollected,
                    isARShow = detailInformationScreenUiState.inkStone.isARShow,
                    description = detailInformationScreenUiState.inkStone.inkStoneDescription,
                    height = detailInformationScreenUiState.inkStone.inkStoneHeight,
                    width = detailInformationScreenUiState.inkStone.inkStoneWidth,
                    length = detailInformationScreenUiState.inkStone.inkStoneLength,
                    name = detailInformationScreenUiState.inkStone.inkStoneName,
                    type = detailInformationScreenUiState.inkStone.inkStoneType,
                    dynastic = detailInformationScreenUiState.inkStone.inkStoneDynasty,
                    relevancyInkStoneList = relevancyInkStoneList2,
                )
            }
            composable(
                route = ARShowDestination.route,
                enterTransition = {
                    expandIn (
                        animationSpec = tween(1000),
                        expandFrom = Alignment.Center
                    ) {
                        IntSize(0,0)
                    }
                },
                exitTransition = {
                    shrinkOut (
                        animationSpec = tween(1000),
                        shrinkTowards = Alignment.Center
                    ){
                        IntSize(0,0)
                    }
                }
            ){
                ARShowScreen(arFilePath = detailInformationScreenUiState.inkStone.arPath)
            }
            composable(route = ImageShowDestination.route){
                ImageShowScreen(
                    imageId = detailInformationScreenUiState.inkStone.intactImageId,
                    inkStoneName = detailInformationScreenUiState.inkStone.inkStoneName,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    )
}

@Composable
fun MainInformationScreen(
    mainNavController: NavController,
    currentNavController: NavController,
    currentColor: Color,
    @DrawableRes currentImage: Int,
    onCollectedChanged : (Boolean) -> Unit,
    isCollected : Boolean ,
    isARShow : Boolean,
    description: String,
    height : String,
    width: String,
    length : String,
    name : String,
    type: String,
    dynastic: String,
    relevancyInkStoneList: List<InkStone>,
){

    val toolbarHeight = 500.dp
    val maxUpPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() - 56.dp.roundToPx().toFloat() }
    // ToolBar 最小向上位移量
    val minUpPx = 0f
    // 偏移折叠工具栏上移高度
    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }
    // 现在，让我们创建与嵌套滚动系统的连接并聆听子 LazyColumn 中发生的滚动
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.value + delta
                toolbarOffsetHeightPx.value = newOffset.coerceIn(-maxUpPx, minUpPx)
                return Offset.Zero
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(top = toolbarHeight)
        ) {

            item {
                TextContent(description = description)
            }
            item {
                SizeTextContent(
                    height = height,
                    width = width,
                    length = length,
                    imageId = currentImage
                )
            }
            item {
                RelevancyInkStone(
                    relevancyInkStoneList = relevancyInkStoneList,
                )
            }
        }

        ScrollableAppBar(
            title = name,
            mainNavController = mainNavController,
            backgroundImageId = currentImage,
            scrollableAppBarHeight = toolbarHeight,
            toolbarOffsetHeightPx = toolbarOffsetHeightPx,
            color = currentColor,
            navController = currentNavController,
            onCollectedChanged = onCollectedChanged,
            isCollected = isCollected,
            isARShow = isARShow,
            type = type,
            dynasty = dynastic
        )
    }
}

@Composable
fun TextContent(
    description : String
){
    Row (
        modifier = Modifier
            .padding(top = 20.dp)
            .paint(
                painter = painterResource(id = R.drawable.bg_element_detail_4),
                alignment = Alignment.CenterEnd,
                contentScale = ContentScale.Fit
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ){
        VerticalText(
            text = "描述",
            color = Color.Black,
            textStyle = TextStyle.Default.copy(fontSize = 20.sp),
            modifier = Modifier.padding(15.dp)
        )

        Spacer(modifier = Modifier.width(30.dp))

        Text(
            text = description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            fontSize = 16.sp
        )
    }
}

@Composable
fun SizeTextContent(
    height : String,
    width : String,
    length : String,
    imageId : Int
){
    Row (
        modifier = Modifier
            .padding(top = 20.dp)
            .paint(
                painter = painterResource(id = R.drawable.bg_element_detail_2),
                alignment = Alignment.CenterStart
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ){
        VerticalText(
            text = "尺寸",
            color = Color.Black,
            textStyle = TextStyle.Default.copy(fontSize = 20.sp),
            modifier = Modifier.padding(start = 15.dp)
        )

        Spacer(modifier = Modifier.width(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(

            ){
                Image(
                    painter = painterResource(id = R.drawable.ic_detail_size),
                    contentDescription = "",
                    modifier = Modifier.size(180.dp),
                    contentScale = ContentScale.FillBounds
                )
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = "",
                    modifier = Modifier
                        .size(130.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillBounds
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = height)
                Text(text = width)
                Text(text = length)
            }
        }
    }
}

@Composable
fun RelevancyInkStone(
    relevancyInkStoneList : List<InkStone>,
){
    Row (
        modifier = Modifier
            .padding(top = 20.dp, end = 15.dp)
            .paint(
                painter = painterResource(id = R.drawable.bg_element_detail_2),
                alignment = Alignment.CenterStart
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ){
        VerticalText(
            text = "相关藏品",
            color = Color.Black,
            textStyle = TextStyle.Default.copy(fontSize = 20.sp),
            modifier = Modifier.padding(start = 15.dp)
        )

        Spacer(modifier = Modifier.width(30.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
                .height(400.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = relevancyInkStoneList[0].intactImageId),
                    contentDescription = "",
                    modifier = Modifier.size(160.dp).clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = painterResource(id = relevancyInkStoneList[1].intactImageId),
                    contentDescription = "",
                    modifier = Modifier.size(160.dp).clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = relevancyInkStoneList[2].intactImageId),
                    contentDescription = "",
                    modifier = Modifier.size(160.dp).clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )
                if (relevancyInkStoneList.size == 4){
                    Image(
                        painter = painterResource(id = relevancyInkStoneList[3].intactImageId),
                        contentDescription = "",
                        modifier = Modifier.size(160.dp).clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

    }
}