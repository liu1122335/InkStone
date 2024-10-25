package com.example.inkstonedemo1.view.detail

import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
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
import com.example.inkstonedemo1.room.inkstone.InkStone
import com.example.inkstonedemo1.view.LoadingScreen
import com.example.inkstonedemo1.intent.viewmodel.MainScreenViewModel
import java.util.Locale

@Composable
fun DetailInformationScreen(
    currentColor: Color,
    onBackButtonClicked: () -> Unit,
    mainScreenViewModel : MainScreenViewModel,
    onRelevancyInkStoneClicked: (InkStone) -> Unit
){
    val detailInformationScreenUiState by mainScreenViewModel.uiState.collectAsState()
    val relevancyInkStoneList by mainScreenViewModel.getAllRelevancyInkStone(
        type = detailInformationScreenUiState.inkStone.inkStoneType,
        id = detailInformationScreenUiState.inkStone.id
    ).collectAsState(initial = InitInkStoneData)
    val navController = rememberNavController()
    var showLoadingScreen by remember { mutableStateOf(false) }
    var clickedInkStone by remember { mutableStateOf(InitInkStoneData[0]) }
    Box(modifier = Modifier.fillMaxSize()){
        NavHost(
            navController = navController,
            startDestination = MainInformationDestination.route,
            builder = {
                composable(route = MainInformationDestination.route){
                    MainInformationScreen(
                        onBackButtonClicked = onBackButtonClicked,
                        currentNavController = navController,
                        currentColor = currentColor,
                        currentImage = detailInformationScreenUiState.inkStone.imageId,
                        onCollectedChanged = {
                            mainScreenViewModel.updateInkStoneIsCollected(isCollected = it)
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
                        relevancyInkStoneList = relevancyInkStoneList,
                        onRelevancyClicked = {
                            clickedInkStone = it
                            showLoadingScreen = true
                        }
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
                    ARShowScreen(
                        arFilePath = detailInformationScreenUiState.inkStone.arPath,
                        navController = navController
                    )
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
        AnimatedVisibility(
            visible = showLoadingScreen,
            modifier = Modifier.fillMaxSize()
        ){
            LoadingScreen(
                onTimeout = {
                    onRelevancyInkStoneClicked(clickedInkStone)
                }
            )
        }
    }
    BackHandler() {
        onBackButtonClicked()
    }
}

lateinit var mTts : TextToSpeech
@Composable
fun MainInformationScreen(
    onRelevancyClicked: (InkStone) -> Unit,
    onBackButtonClicked : () -> Unit,
    currentNavController: NavController,
    currentColor: Color,
    @DrawableRes currentImage: Int,
    onCollectedChanged : (Boolean) -> Unit,
    isCollected : Boolean,
    isARShow : Boolean,
    description: String,
    height : String,
    width: String,
    length : String,
    name : String,
    type: String,
    dynastic: String,
    relevancyInkStoneList: List<InkStone>,
    lifecycleOwner : LifecycleOwner = LocalLifecycleOwner.current
){
    var isSpeak by remember { mutableStateOf(false) }
    mTts = TextToSpeech(
        LocalContext.current
    ) { status ->
        if (status == TextToSpeech.SUCCESS) {
            //设置首选语言为中文,注意，语言可能是不可用的，结果将指示此
            val result = mTts.setLanguage(Locale.CHINA);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED
            ) {
                //语言数据丢失或不支持该语言。
                Log.d("tTms", "语言数据丢失或不支持该语言");
            }
        } else {
            // 初始化失败
            Log.e("tTms", "初始化失败");
        }
    }
    val onStop : () -> Unit = {
        mTts.stop()
        mTts.shutdown()
    }
    val currentOnStop by rememberUpdatedState(onStop)
    val toolbarHeight = 500.dp
    val maxUpPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() - 60.dp.roundToPx().toFloat() }
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
                    onRelevancyClicked = onRelevancyClicked
                )
            }
        }

        ScrollableAppBar(
            onBackButtonClicked = onBackButtonClicked,
            title = name,
            backgroundImageId = currentImage,
            scrollableAppBarHeight = toolbarHeight,
            toolbarOffsetHeightPx = toolbarOffsetHeightPx,
            color = currentColor,
            navController = currentNavController,
            onCollectedChanged = onCollectedChanged,
            isCollected = isCollected,
            isARShow = isARShow,
            type = type,
            dynasty = dynastic,
            onSpeakClicked = {
                if (isSpeak){
                    mTts.stop()
                }else{
                    mTts.speak(description,TextToSpeech.QUEUE_FLUSH,null)
                }
                isSpeak = !isSpeak
            }
        )
        DisposableEffect(lifecycleOwner){
            val observer = LifecycleEventObserver{_,event->
                if (event == Lifecycle.Event.ON_STOP){
                    currentOnStop()
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
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
            textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.font_1)), fontSize = 20.sp),
            modifier = Modifier.padding(15.dp)
        )

        Spacer(modifier = Modifier.width(30.dp))

        Text(
            text = description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.font_1))
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
            textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.font_1)), fontSize = 20.sp),
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
                Text(text = height, fontFamily = FontFamily(Font(R.font.font_1)))
                Text(text = width,fontFamily = FontFamily(Font(R.font.font_1)))
                Text(text = length,fontFamily = FontFamily(Font(R.font.font_1)))
            }
        }
    }
}

@Composable
fun RelevancyInkStone(
    relevancyInkStoneList : List<InkStone>,
    onRelevancyClicked: (InkStone) -> Unit
){
    Row (
        modifier = Modifier
            .padding(top = 20.dp, end = 15.dp)
            .paint(
                painter = painterResource(id = R.drawable.bg_element_detail_1),
                alignment = Alignment.CenterStart
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ){
        VerticalText(
            text = "相关藏品",
            color = Color.Black,
            textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.font_1)), fontSize = 20.sp),
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
                    modifier = Modifier.size(140.dp).clip(RoundedCornerShape(10.dp))
                        .clickable { onRelevancyClicked(relevancyInkStoneList[0]) },
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = painterResource(id = relevancyInkStoneList[1].intactImageId),
                    contentDescription = "",
                    modifier = Modifier.size(140.dp).clip(RoundedCornerShape(10.dp))
                        .clickable { onRelevancyClicked(relevancyInkStoneList[1]) },
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
                    modifier = Modifier.size(140.dp).clip(RoundedCornerShape(10.dp))
                        .clickable { onRelevancyClicked(relevancyInkStoneList[2]) },
                    contentScale = ContentScale.Crop
                )
                if (relevancyInkStoneList.size == 4){
                    Image(
                        painter = painterResource(id = relevancyInkStoneList[3].intactImageId),
                        contentDescription = "",
                        modifier = Modifier.size(140.dp).clip(RoundedCornerShape(10.dp))
                            .clickable { onRelevancyClicked(relevancyInkStoneList[3]) },
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

    }
}