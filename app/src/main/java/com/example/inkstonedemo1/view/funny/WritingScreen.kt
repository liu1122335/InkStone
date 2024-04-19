package com.example.inkstonedemo1.view.funny

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.view.MotionEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.inkstonedemo1.ui.theme.md_theme_light_tertiaryContainer
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.component.AppToolsBar
import com.example.inkstonedemo1.utils.WritingBoardConfig.ITEM_SIZE
import com.example.inkstonedemo1.viewmodel.WriteScreenViewModel
import com.example.inkstonedemo1.viewmodel.WritingBoardViewAction
import com.example.inkstonedemo1.viewmodel.WritingBoardViewStates

@ExperimentalComposeUiApi
@Composable
fun WritingScreen(
    navController: NavController,
    onPreview: () -> Unit
) {
    val viewModel = viewModel<WriteScreenViewModel>()
    val states by viewModel.viewStates.collectAsState()
    val itemSize = with(LocalDensity.current) { ITEM_SIZE.dp.toPx() }
    val bitmap = remember {
        Bitmap.createBitmap(
            itemSize.toInt(), itemSize.toInt(), Bitmap.Config.ARGB_8888
        )
    }
    val newCanvas = remember { Canvas(bitmap) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppToolsBar(title = "临池", onBack = { navController.popBackStack() })
            BoardContent(viewModel, bitmap, newCanvas)
            DividerTab(viewModel, bitmap, newCanvas)
            ImageList(viewModel)
        }
        ConfirmBtn(modifier = Modifier.align(Alignment.BottomCenter), states, onPreview)
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@ExperimentalComposeUiApi
@Composable
fun BoardContent(
    viewModel: WriteScreenViewModel,
    bitmap: Bitmap,
    newCanvas: Canvas
) {
    val states by viewModel.viewStates.collectAsState()
    BoxWithConstraints(
        modifier = Modifier
            .size(ITEM_SIZE.dp)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_writing),
            modifier = Modifier
                .fillMaxSize(),
            contentDescription = ""
        )
        WritingBoard(viewModel = viewModel, states = states, bitmap = bitmap, newCanvas = newCanvas)
    }
}

@Composable
fun ConfirmBtn(
    modifier: Modifier,
    states: WritingBoardViewStates,
    onPreview: () -> Unit
) {
    Button(
        onClick = {
            if (states.bitmapList.isNotEmpty()) {
                onPreview.invoke()
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = md_theme_light_tertiaryContainer)
    ) {
        Text(text = "生成墨宝", color = Color.Black)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ImageList(viewModel: WriteScreenViewModel) {
    val states by viewModel.viewStates.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            states.bitmapList.forEach {
                Image(
                    bitmap = it.asImageBitmap(),
                    modifier = Modifier.size(80.dp),
                    contentDescription = ""
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.ic_backspace),
            contentDescription = "",
            modifier = Modifier
                .align(
                    Alignment.TopEnd
                )
                .padding(start = 0.dp, top = 16.dp, end = 15.dp, 0.dp)
                .clickable {
                    viewModel.dispatch(WritingBoardViewAction.DeleteItem)
                }
        )
    }
}

@Composable
fun DividerTab(viewModel: WriteScreenViewModel, bitmap: Bitmap, newCanvas: Canvas) {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFCCCCCC))
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_delete),
            modifier = Modifier
                .wrapContentSize()
                .clickable {
                    newCanvas.drawColor(android.graphics.Color.WHITE, PorterDuff.Mode.CLEAR)
                },
            contentDescription = ""
        )
        Image(
            painter = painterResource(id = R.drawable.ic_sure),
            modifier = Modifier
                .wrapContentSize()
                .clickable {
                    val curBitmap = bitmap.copy(bitmap.config, true)
                    viewModel.dispatch(WritingBoardViewAction.ConfirmItem(curBitmap))
                    newCanvas.drawColor(android.graphics.Color.WHITE, PorterDuff.Mode.CLEAR)
                },
            contentDescription = ""
        )
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFCCCCCC))
    )
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@ExperimentalComposeUiApi
@Composable
fun WritingBoard(
    viewModel: WriteScreenViewModel,
    states: WritingBoardViewStates,
    bitmap: Bitmap,
    newCanvas: Canvas
) {
    val paint = remember { Paint().apply { color = android.graphics.Color.BLACK } }
    BoxWithConstraints(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInteropFilter(onTouchEvent = {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            viewModel.dispatch(WritingBoardViewAction.ActionDown(it))
                        }
                        MotionEvent.ACTION_MOVE -> {
                            viewModel.dispatch(WritingBoardViewAction.ActionMove(it))
                        }
                        MotionEvent.ACTION_UP -> {
                            states.pointList.forEach { point ->
                                newCanvas.drawCircle(point.x, point.y, point.width, paint)
                            }
                            viewModel.dispatch(WritingBoardViewAction.ActionUp(it))
                        }
                    }
                    true
                })
        ) {
            drawImage(bitmap.asImageBitmap())
            states.pointList.forEach {
                drawCircle(Color.Black, it.width, Offset(it.x, it.y))
            }
        }
    }
}