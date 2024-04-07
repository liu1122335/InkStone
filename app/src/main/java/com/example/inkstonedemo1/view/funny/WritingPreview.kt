package com.example.inkstonedemo1.view.funny

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.component.AppToolsBar
import com.example.inkstonedemo1.utils.BitmapUtils
import com.example.inkstonedemo1.utils.WritingBoardConfig.ITEM_SIZE
import com.example.inkstonedemo1.viewmodel.WriteScreenViewModel

@Composable
fun WritingPreviewScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AppToolsBar(title = "长按保存笔墨", onBack = {
            onBack.invoke()
        })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WritingPreview()
        }
    }
    BackHandler {
        onBack.invoke()
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun WritingPreview(

) {
    val viewModel = viewModel<WriteScreenViewModel>()
    val states by viewModel.viewStates.collectAsState()
    val itemSize = with(LocalDensity.current) { ITEM_SIZE.dp.toPx() }
    val bitmap = remember {
        Bitmap.createBitmap(
             itemSize.toInt(), (states.bitmapList.size * itemSize).toInt(), Bitmap.Config.ARGB_8888
        )
    }
    val bgBitmap = BitmapFactory.decodeResource(LocalContext.current.resources,R.drawable.bg_writing_preview)
    val newBgBitmap = Bitmap.createScaledBitmap(bgBitmap,itemSize.toInt(),(states.bitmapList.size * itemSize).toInt(),true)
    val newCanvas = remember { android.graphics.Canvas(bitmap) }
    val paint = remember { Paint() }
    val context = LocalContext.current
    BoxWithConstraints(
        modifier = Modifier
            .width(ITEM_SIZE.dp)
            .height((ITEM_SIZE * states.bitmapList.size).dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        BitmapUtils.saveBitmapToGallery(context, bitmap, "笔墨")
                        Toast
                            .makeText(context, "笔墨已保存", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
            }
    ) {
        newCanvas.drawBitmap(newBgBitmap,0f,0f,paint)
        for (i in states.bitmapList.indices) {
            newCanvas.drawBitmap(states.bitmapList[i], 0f, itemSize * i, paint)
        }

        Canvas(modifier = Modifier
            .fillMaxSize()
            .align(Alignment.Center),
        ) {
            drawImage(bitmap.asImageBitmap(), Offset.Zero)
        }
    }
}