package com.example.inkstonedemo1.view.funny

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.utils.BitmapUtils
import com.example.inkstonedemo1.utils.WritingBoardConfig.ITEM_SIZE
import com.example.inkstonedemo1.viewmodel.UserScreenViewModel
import com.example.inkstonedemo1.viewmodel.WriteScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WritingPreviewScreen(
    onBack: () -> Unit,
    userScreenViewModel: UserScreenViewModel
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "笔墨预览")
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onBack.invoke() }
                    ){
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            userScreenViewModel.insertCalligraphy(bitmap)
                            Toast.makeText(context, "笔墨已保存", Toast.LENGTH_SHORT)
                                .show()
                        }
                    ){
                        Icon(
                            painter = painterResource(R.drawable.ic_save),
                            contentDescription = "",
                        )
                    }
                    IconButton(
                        onClick = {
                            BitmapUtils.saveBitmapToGallery(context, bitmap, "inkstone")
                            Toast.makeText(context, "笔墨已下载至相册", Toast.LENGTH_SHORT)
                                .show()
                        }
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_download),
                            contentDescription = "",
                        )
                    }
                }
            )
        }
    ) {innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BoxWithConstraints(
                    modifier = Modifier
                        .width(ITEM_SIZE.dp)
                        .height((ITEM_SIZE * states.bitmapList.size).dp)
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
        }
    }
    BackHandler {
        onBack.invoke()
    }

}